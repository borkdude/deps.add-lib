(defproject io.github.borkdude/deps.add-lib "0.0.1"
  :dependencies [[org.clojure/clojure "1.12.0-alpha2"]
                 [borkdude/deps.clj "1.11.1.1273-3"]]
  :deploy-repositories [["clojars" {:url "https://clojars.org/repo"
                                    :username :env/clojars_user
                                    :password :env/clojars_pass
                                    :sign-releases false}]])
