# deps.add-lib

[![Clojars Project](https://img.shields.io/clojars/v/io.github.borkdude/deps.add-lib.svg)](https://clojars.org/io.github.borkdude/deps.add-lib)

Clojure 1.12's `add-lib` combined with [deps.clj](https://github.com/borkdude/deps.clj).

This projects brings Clojure's new `add-lib` function to leiningen and/or other
environments that do not have a or a specific version of the clojure CLI
installed.

## Status

Experimental, use as a dev-only tool.

## Usage

To use this, add `io.github.borkdude/deps.add-lib {:mvn/version "<latest>"}` to your
`deps.edn` or `project.clj`.

E.g. in your `project.clj`:

``` clojure
(defproject my-project "0.0.1"
  :dependencies [[org.clojure/clojure "1.12.0-alpha2"]
                 [io.github.borkude/deps.add-lib "0.0.1"]])
```

And then REPL away:

``` clojure
(require '[borkdude.deps.add-lib :refer [add-lib]])
(add-lib 'medley/medley)
(require 'medley.core) ;; bingo
```
