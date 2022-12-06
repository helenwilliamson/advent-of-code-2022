(ns advent-of-code-2022.puzzle-6
  (:gen-class)
  (:require [clojure.string :as str]))

(defn read-input
  ([] (read-input "puzzle-6"))
  ([name]
   (-> (slurp (str "resources/" name)))))

(defn find-marker
  [size data]
  (->> (partition size 1 data)
       (keep-indexed #(if (= size (count (set %2))) %1))
       first
       (+ size)))

(defn version-1
  []
  (->> (read-input)
       (find-marker 4)))

(defn version-2
  []
  (->> (read-input "puzzle-6")
       (find-marker 14)))
