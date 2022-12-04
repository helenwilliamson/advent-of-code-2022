(ns advent-of-code-2022.puzzle-4
  (:gen-class)
  (:require [clojure.string :as str])
  (:require [clojure.set :as set]))

(defn read-input
  ([] (read-input "puzzle-4"))
  ([name]
   (->> (-> (slurp (str "resources/" name))
            (str/split #"\n"))
        (map (fn [pair] (->> (str/split pair #",")
                             (map #(str/split %1 #"-"))
                             (map (fn [[first second]]
                                    (range (Integer/parseInt first)
                                           (+ 1 (Integer/parseInt second)))))
                             (map #(set %1))))))))

(defn contains-all?
  [[first second]]
  (or (empty? (set/difference first second))
      (empty? (set/difference second first))))

(defn in-both?
  [[first second]]
  (not (empty? (filter #(contains? second %1) first))))

(defn version-1
  []
  (->> (read-input "puzzle-4")
       (filter #(contains-all? %1))
       (count)))

(defn version-2
  []
  (->> (read-input "puzzle-4")
       (filter #(in-both? %1))
       (count)))
