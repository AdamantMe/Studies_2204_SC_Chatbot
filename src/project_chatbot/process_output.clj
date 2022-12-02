(ns project-chatbot.process-output
  (:use [org.clojars.cognesence.matcher.core])
  (:require [clojure.string :as string]
            [project-chatbot.data-ops :as data]))

(defmatch rules []
  ((biking true ?park) :=> (mout '(Yes, you can bike in ?park)))
  ((biking false ?park) :=> (mout '(No, you can not bike in ?park))))

(defn process_output_string []
  (let [activity (deref (:activity data/LastQuery))
        availability (data/get-activity-availability activity)
        park (string/capitalize (name (deref (:park data/LastQuery))))]
    (println (string/join " " (rules (list (symbol activity) availability park))))))