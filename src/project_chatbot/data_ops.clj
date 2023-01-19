(ns project-chatbot.data-ops
  (:require [clojure.data.json :as json]))

(def parks-data (json/read-str (slurp "resources/parks_data.json")
                               :key-fn keyword))

;; Object that holds data for "park" and "activity" that user asked about the last. 
;; Important to have, since user won't specify the name of the park every time they ask a question. 
;; Instead, in most cases, user will have follow up questions about the same park.
(defrecord UserQuery [park activity])
(defn init-question []
  (UserQuery. (ref nil) (ref nil)))
(def LastQuery (init-question))

;; Set the park which user has asked about the last.
(defn last-query-set-park [park]
  (dosync (ref-set (:park LastQuery) park)))

;; Set the activity which user has asked about the last.
(defn last-query-set-activity [act]
  (dosync (ref-set (:activity LastQuery) act)))

;; Returns a corresponding value from raw data JSON file, under specific park, under given activity.
(defn get-activity-availability [activity]
  (let [parkName (deref (:park LastQuery))
        park (parkName parks-data)]
    (park (keyword activity))))

;; Returns a JSON file friendly form of any keyword extrapolated from user's question.
;; E.g. User might ask "Is there a soccer field?", instead of "Can I PLAY soccer in that park?".
;; Both of these questions should be given the same reply. Hence the final keyword, to use later in JSON, must be the same ("sports" in this case).
(defn get-activity-synonym-from-keyword [_keyword]
  (cond
    (= _keyword '(ride)) :biking
    (= _keyword '(riding)) :biking
    (= _keyword '(bike)) :biking
    (= _keyword '(ride bike)) :biking
    (= _keyword '(skate)) :skating
    (= _keyword '(play)) :playground
    (= _keyword '(playing)) :playground
    (= _keyword '(sport)) :sports
    (= _keyword '(soccer)) :sports
    (= _keyword '(football)) :sports
    (= _keyword '(frisbee)) :sports
    (= _keyword '(play soccer)) :sports
    (= _keyword '(play football)) :sports
    (= (name (first _keyword)) "get to") :transportation
    (= (name (first _keyword)) "get from") :transportation
    (= (name (first _keyword)) "get there") :transportation
    (= _keyword '(transport)) :transportation
    (= (name (first _keyword)) "park a car") :parking
    (= _keyword '(toilet)) :wc
    (= _keyword '(restroom)) :wc
    (= _keyword '(dog)) :dogs
    (= _keyword '(interesting)) :attractions
    (= (name (first _keyword)) "things to do") :attractions
    (= _keyword '(food)) :restaurant
    (= (name (first _keyword)) "to eat") :restaurant
    (= _keyword '(ski)) :skiing
    :else (keyword (first _keyword))))

(defn get-parkname-synonym-from-keyword [_keyword]
  (cond
    (= _keyword '(bertramka)) :bertramka
    (= _keyword '(frantiskanska)) :frantiskanska-zahrada
    (= _keyword '(obora)) :obora-hvezda
    (= _keyword '(kampa)) :kampa
    (= _keyword '(kinskeho)) :kinskeho-zahrada
    (= _keyword '(klamovka)) :klamovka
    (= _keyword '(ladronka)) :ladronka
    (= _keyword '(letna)) :letna
    (= _keyword '(petrin)) :petrin
    (= _keyword '(riegrovy)) :riegrovy-sady
    (= _keyword '(stromovka)) :stromovka
    (= _keyword '(vysehrad)) :vysehrad
    :else nil))