(ns advent-of-code-2022.puzzle-5
  (:gen-class)
  (:require [clojure.string :as str]))

(defn read-crates
  [data]
  (let [rows (str/split data #"\n")
        row-indexes (last rows)
        row-crates (butlast rows)
        indexes (str/split (str/trim row-indexes) #"[ ]+")]
    (->> (map (fn [index]
                (let [index-of-index (.indexOf row-indexes index)]
                  (->> row-crates
                       (map #(if (> (count %1) index-of-index)
                               (str/trim (subs %1 index-of-index (+ 1 index-of-index)))))
                       (filter #(not (str/blank? %1))))))
              indexes)
         (vec))))

(defn read-procedure
  [data]
  (->> (str/split data #"\n")
       (map #(re-seq #"\d+" %1))
       (map (fn [move] (map #(Integer/parseInt %1) move)))))

(defn read-input
  ([] (read-input "puzzle-5"))
  ([name]
   (->> (-> (slurp (str "resources/" name))
            (str/split #"\n\n"))
        ((fn [data] (let [crates (first data) procedure (second data)]
                      {:crates (read-crates crates)
                       :procedure (read-procedure procedure)}))))))

(defn move
  [crates [number-of-crates from to] func]
  (let [from-index (- from 1)
        to-index (- to 1)
        new-from (drop number-of-crates (crates from-index))
        new-to (concat (func (take number-of-crates (crates from-index))) (crates to-index))]
    (assoc crates from-index new-from to-index new-to)))

(defn rearrange
  [func data]
  (loop [crates (:crates data) procedure (:procedure data)]
    (if (empty? procedure)
      crates
      (recur (move crates (first procedure) func) (rest procedure)))))

(defn version-1
  []
  (->> (read-input "puzzle-5")
       (rearrange reverse)
       (map #(take 1 %1))
       flatten
       str/join))

(defn version-2
  []
  (->> (read-input "puzzle-5")
       (rearrange identity)
       (map #(take 1 %1))
       flatten
       str/join))
