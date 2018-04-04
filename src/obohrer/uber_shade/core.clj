(ns obohrer.uber-shade.core
  (:require [clojure.java.io :as io])
  (:import [org.apache.maven.plugins.shade Shader DefaultShader ShadeRequest]
           [org.apache.maven.plugins.shade.relocation Relocator SimpleRelocator]
           [org.apache.maven.plugins.shade.filter Filter SimpleFilter]
           [org.codehaus.plexus.logging Logger]
           [org.codehaus.plexus.logging.console ConsoleLogger]))


(defn- ^Relocator simple-relocation
  [{:keys [from to includes excludes]
    :or {includes [] excludes []}}]
  (SimpleRelocator. (name from) (name to)
                    (map name includes)
                    (map name excludes)))


(defn- ^ShadeRequest shade-request
  [{:keys [jar shaded-jar relocations filters shade-source-content]
    :or {relocations []
         shade-source-content false}}]
  (doto (ShadeRequest.)
    (.setJars #{(io/as-file jar)})
    (.setUberJar (io/as-file shaded-jar))
    (.setRelocators (map simple-relocation relocations))
    (.setFilters [])
    (.setResourceTransformers [])
    (.setShadeSourcesContent shade-source-content)))


(defn- ^Shader shader
  "Build a shader and setup it's logging otherwise it will crash."
  []
  (doto (DefaultShader.)
    (.enableLogging (ConsoleLogger. Logger/LEVEL_INFO "uber-shade"))))


(defn shade
  "Runs the maven shade plugin using the relocations to configure SimpleRelocator"
  [{:keys [jar shaded-jar relocations shade-source-content]
    :as shade-request-config}]
  (.shade (shader) (shade-request shade-request-config)))
