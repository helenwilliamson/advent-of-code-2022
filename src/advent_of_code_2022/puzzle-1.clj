(ns advent-of-code-2022.puzzle-1
  (:gen-class)
  (:require [clojure.string :as str]))

(defn read-input
  ([] (read-input "puzzle-1"))
  ([name]
   (->> (-> (slurp (str "resources/" name))
            (str/split #"\n\n"))
        (map #(str/split %1 #"\n"))
        (map #(map (fn [x] (Integer/parseInt x)) %1)))))

(defn calories-by-max
  []
  (->> (read-input)
       (map #(reduce + %1))
       (sort >)))

(defn version-1
  []
  (first (calories-by-max)))

(defn version-2
  []
  (->> (calories-by-max)
       (take 3)
       (reduce +)))
