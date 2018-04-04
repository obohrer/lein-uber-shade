# lein-uber-shade

A Leiningen plugin to relocate classes/packages using the Maven Shade Plugin.
`lein-uber-shade` can be useful if you submit your jar which will run in an environment which contains incompatible provided dependencies with your project.
This will relocate classes packages matchings the relocations specified and update the paths in the uberjar to point to these new packages. Use with care.

This is inspired by https://github.com/redbadger/shade which allows you to relocate clojure namespaces

![](https://clojars.org/lein-uber-shade/latest-version.svg)

## Usage

Use this for project-level plugins:

Put `[lein-uber-shade "0.1.0"]` into the `:plugins` vector of your project.clj.

To build the uberjar with shaded classes/packages/dependencies:

    $ lein shade-uberjar

Configuration of the plugin:
Add `:uber-shade` to your project to configure relocations. Example:

``` clojure
 :uber-shade {:relocations [{:from io.netty :to io.shaded.netty}]}
```
In this example we relocate all classes starting by io.netty to io.shaded.netty

## License

Copyright © 2018 obohrer

Distributed under the Eclipse Public License either version 2.0 or (at
your option) any later version.
