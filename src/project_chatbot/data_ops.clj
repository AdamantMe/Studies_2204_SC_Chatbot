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

;; Return a specific form of a word, to match json file's attributes' names.
(defn get-synonym-from-keyword [_keyword]
  (cond
    (= _keyword '(ride)) :biking
    (= _keyword '(riding)) :biking
    (= _keyword '(bike)) :biking
    (= _keyword '(skate)) :skating
    (= _keyword '(sport)) :sports
    (= _keyword '(play)) :playground
    (= _keyword '(playing)) :playground
    (= _keyword '(get to)) :transportation
    (= _keyword '(get from)) :transportation
    (= _keyword '(transport)) :transportation
    (= _keyword '(park a car)) :parking
    (= _keyword '(toilet)) :wc
    (= _keyword '(restroom)) :wc
    (= _keyword '(dog)) :dogs
    (= _keyword '(interesting)) :attractions
    (= _keyword '(things to do)) :attractions
    (= _keyword '(food)) :restaurant
    (= _keyword '(to eat)) :restaurant
    (= _keyword '(ski)) :skiing
    :else (keyword (first _keyword))))