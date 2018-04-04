(ns leiningen.shade-uberjar
  (:require [obohrer.uber-shade.core :as core]
            [leiningen.core.main :as main]
            [leiningen.jar :as jar]
            [leiningen.uberjar :as uberjar]
            [clojure.java.io :as io]))



(defn shade-uberjar
  [project & args]
  (let [output-dir (io/file (:target-path project) "shaded-uberjar")
        uberjar-file (uberjar/uberjar project)
        shaded-jar-file (str (io/file output-dir
                                      (-> project jar/get-jar-filename io/file .getName)))
        {:keys [relocations shade-source-content]} (:uber-shade project)]
    (core/shade {:jar uberjar-file :shaded-jar shaded-jar-file
                 :relocations relocations
                 :shade-source-content shade-source-content})
    (main/info "Created" shaded-jar-file)
    shaded-jar-file))
