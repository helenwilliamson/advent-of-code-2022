(ns advent-of-code-2022.puzzle-2
  (:gen-class)
  (:require [clojure.string :as str])
  (:require [clojure.set :as set]))

(def conversion-move {"A" :rock
                      "B" :paper
                      "C" :scissors
                      "X" :rock
                      "Y" :paper
                      "Z" :scissors})

(def conversion-outcome {"X" :lose
                         "Y" :draw,
                         "Z" :win})

(def winning {:rock :scissors
              :paper :rock
              :scissors :paper})

(def losing (set/map-invert winning))

(def score-for-play {:rock 1
                     :paper 2
                     :scissors 3})

(defn read-input
  ([] (read-input "puzzle-2"))
  ([name]
   (->> (-> (slurp (str "resources/" name))
            (str/split #"\n"))
        (map #(str/split %1 #" ")))))

(defn score
  [{opp :opp, me :me}]
  (->
   (cond (= opp me) 3
         (= (winning me) opp) 6
         :else 0)
   (+ (score-for-play me))))

(defn move
  [opp outcome]
  (->
   (case outcome
     :draw opp
     :win (losing opp)
     :lose (winning opp))))

(defn version-1
  []
  (->> (read-input)
       (map (fn [[opp me]] {:opp (conversion-move opp) :me (conversion-move me)}))
       (map #(score %1))
       (reduce +)))

(defn version-2
  []
  (->> (read-input)
       (map (fn [[opp outcome]] {:opp (conversion-move opp) :outcome (conversion-outcome outcome)}))
       (map (fn [{opp :opp outcome :outcome}] {:opp opp :me (move opp outcome)}))
       (map #(score %1))
       (reduce +)))
