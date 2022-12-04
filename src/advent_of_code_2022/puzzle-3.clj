(ns advent-of-code-2022.puzzle-3
  (:gen-class)
  (:require [clojure.string :as str])
  (:require [clojure.set :as set]))

(def priorities [\a \b \c \d \e \f \g \h \i \j \k \l \m \n \o \p \q \r \s \t \u \v \w \x \y \z \A \B \C \D \E \F \G \H \I \J \K \L \M \N \O \P \Q \R \S \T \U \V \W \X \Y \Z])

(defn read-input
  ([] (read-input "puzzle-3"))
  ([name]
   (-> (slurp (str "resources/" name))
            (str/split #"\n"))))

(defn in-both
  [first second]
  (filter #(contains? second %1) first))

(defn version-1
  []
  (->> (read-input "puzzle-3-basic")
       (map #(->> (split-at (/ (count %1) 2) %1)
                   (map (fn [list] (set list)))))
       (map (fn [[first second]] (in-both first second)))
       (flatten)
       (map #(+ 1 (.indexOf priorities %1)))
       (reduce +)))

(defn version-2
  []
  (->> (read-input "puzzle-3")
       (map #(set %1))
       (partition 3)
       (map (fn [[first second third]] (in-both (in-both first second) third)))
       (flatten)
       (map #(+ 1 (.indexOf priorities %1)))
       (reduce +)))
