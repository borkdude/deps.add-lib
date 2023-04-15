# lein.repl.deps

Clojure 1.12's `add-lib` feature for leiningen.

This is currently just a hack to show how you can tweak
`clojure.tools.deps.interop/invoke-tool` to your liking.  In this project, I
bound it to a function which invokes
[deps.clj](https://github.com/borkdude/deps.clj), an implementation of the
clojure CLI in clojure itself.

As such, `lein.repl.deps/add-lib` will work in leiningen, without even having
the clojure CLI installed.

Don't use in production, just in development and maybe not even that :).

## Usage

To use this, currently just clone this repo and call `lein install` to locally install it.

Then in your `project.clj`:

``` clojure
(defproject my-project "0.0.1"
  :dependencies [[org.clojure/clojure "1.12.0-alpha2"]
                 [lein-repl-deps "0.0.1"]])
```

And then REPL away:

``` clojure
(require '[lein.repl.deps :refer [add-lib]])
(add-lib 'medley/medley)
(require 'medley.core) ;; bingo
```
