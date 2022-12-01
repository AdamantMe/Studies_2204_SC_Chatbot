(ns project-chatbot.data-ops
  (:require [clojure.data.json :as json]))

(def parks-data (json/read-str (slurp "resources/parks_data.json")
                               :key-fn keyword))

(defrecord UserQuery [park activity query])
(defn init-question []
  (UserQuery. (ref nil) (ref nil) (ref nil)))
(def LastQuery (init-question))

(defn last-query-set-park [park]
  (dosync (ref-set (:park LastQuery) park)))

(defn last-query-set-activity [act]
  (dosync (ref-set (:activity LastQuery) act)))

(def park-bertramka-data (get parks-data :bertramka))


(defn data-init []
;;   (eval parks-data)
;;   (println parks-data)
  )

(defn get-synonym-from-keyword [keyword]
  (cond
    (= keyword '(ride)) :biking
    (= keyword '(riding)) :biking
    (= keyword '(bike)) :biking
    (= keyword '(skate)) :skating
    (= keyword '(sport)) :sports
    (= keyword '(play)) :playground
    (= keyword '(playing)) :playground
    (= keyword '(get to)) :transportation
    (= keyword '(get from)) :transportation
    (= keyword '(transport)) :transportation
    (= keyword '(park a car)) :parking
    (= keyword '(toilet)) :wc
    (= keyword '(restroom)) :wc
    (= keyword '(dog)) :dogs
    (= keyword '(interesting)) :attractions
    (= keyword '(things to do)) :attractions
    (= keyword '(food)) :restaurant
    (= keyword '(to eat)) :restaurant
    (= keyword '(ski)) :skiing
    :else (symbol keyword)))