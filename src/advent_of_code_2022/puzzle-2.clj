(ns advent-of-code-2022.puzzle-2
  (:gen-class)
  (:require [clojure.string :as str]))

(def conversion
  {"A" :rock, "B" :paper, "C" :scissors, "X" :rock, "Y" :paper, "Z" :scissors})

(def score-for-play
  {:rock 1, :paper 2, :scissors 3})

(defn read-input
  ([] (read-input "puzzle-2"))
  ([name]
   (->> (-> (slurp (str "resources/" name))
            (str/split #"\n"))
        (map #(str/split %1 #" "))
        (map (fn [[opp me]] {:opp (conversion opp) :me (conversion me)})))))

(defn score
  [{opp :opp, me :me}]
  (->
   (cond (= opp me) 3
         (= me :rock) (if (= opp :scissors) 6 0)
         (= me :scissors) (if (= opp :paper) 6 0)
         (= me :paper) (if (= opp :rock) 6 0))
   (+ (score-for-play me))))

(defn version-1
  []
  (->> (read-input)
       (map #(score %1))
       (reduce +)))
