(defproject lein-uber-shade "0.1.2"
  :description "Lein plugin to relocate classes/packages within uberjars"
  :url "https://github.com/obohrer/lein-uberjar-shade"
  :license {:name "Eclipse Public License" :url "http://www.eclipse.org/legal/epl-v20.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.apache.maven.plugins/maven-shade-plugin "3.2.2"]]
  :deploy-repositories [["releases"  {:sign-releases false :url "https://repo.clojars.org"}]
                        ["snapshots" {:sign-releases false :url "https://repo.clojars.org"}]]
  :eval-in-leiningen true)
