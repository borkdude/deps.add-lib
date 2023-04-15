(ns lein.repl.deps
  (:require
   [clojure.repl.deps :as deps]
   [borkdude.deps :as deps.clj]
   [clojure.tools.deps.interop :as tool]
   [clojure.edn :as edn]
   [clojure.java.process :as proc]
   [clojure.java.io :as io]))

(defn- invoke-tool
  {:added "1.12"}
  [{:keys [tool-name tool-alias fn args preserve-envelope]
    :as opts
    :or {preserve-envelope false}}]
  (when-not (or tool-name tool-alias) (throw (ex-info "Either :tool-alias or :tool-name must be provided" (or opts {}))))
  (when-not (symbol? fn) (throw (ex-info (str "fn should be a symbol " fn) (or opts {}))))
  (let [args (assoc args :clojure.exec/invoke :fn)
        ;; this is a hack since in lein there is no basis and we just make this up:
        args (assoc args :procurer {:mvn/repos {"central" {:url "https://repo1.maven.org/maven2/"}, "clojars" {:url "https://repo.clojars.org/"}}
                                    :mvn/local-repo (str (io/file (System/getProperty "user.home") ".m2"))})
        _ (when (:debug opts) (println "args" args))
        command-strs [(str "-T" (or tool-alias tool-name)) (pr-str fn) (pr-str args)]
        _ (when (:debug opts) (apply println "Invoking: " command-strs))
        envelope (edn/read-string
                  (binding [deps.clj/*process-fn* (clojure.core/fn [cmd]
                                                    (apply proc/exec cmd))]
                    (apply deps.clj/-main command-strs)))]
    (if preserve-envelope
      envelope
      (let [{:keys [tag val]} envelope
            parsed-val (edn/read-string val)]
        (if (= :ret tag)
          parsed-val
          (throw (ex-info (:cause parsed-val) (or parsed-val {}))))))))

(defn add-lib
  [& args]
  (binding [tool/invoke-tool invoke-tool]
    (apply deps/add-lib args)))

;;;; Scratch

(comment
  (add-lib 'medley/medley)
  (require 'medley.core)
  )
