(ns project-chatbot.process-output
  (:use [org.clojars.cognesence.matcher.core])
  (:require [clojure.string :as string]
            [project-chatbot.data-ops :as data]))

(defmatch rules []
  ((biking true ?park) :=> (mout '(Yes, you can bike in ?park)))
  ((biking false ?park) :=> (mout '(No, you can not bike in ?park)))
  ((wc true ?park) :=> (mout '(Yes, there are public restrooms in ?park)))
  ((wc false ?park) :=> (mout '(No, there are no public restrooms in ?park)))
  ((attractions ?attractions ?park) :=> (mout '(In ?park one can access ?attractions)))
  ((transportation ?transportation ?park) :=> (mout '(?transportation)))
  ((restaurant nil ?park) :=> (mout '(There are no restaurants in ?park)))
  ((skating true ?park) :=> (mout '(Yes, you can skate in ?park)))
  ((skating false ?park) :=> (mout '(No, you can not skate in ?park)))
  ((playground true ?park) :=> (mout '(Yes, there is a playground in ?park)))
  ((playground false ?park) :=> (mout '(No, there is not a playground in ?park)))
  ((parking true ?park) :=> (mout '(Yes, there is parking at ?park)))
  ((parking false ?park) :=> (mout '(No, there is not parking at ?park)))
  ((sports true ?park) :=> (mout '(Yes, you can play sports in ?park)))
  ((sports false ?park) :=> (mout '(No, you can not play sports in ?park)))
  )

(defn process_output_string []
  (let [activity (deref (:activity data/LastQuery))
        availability (data/get-activity-availability activity)
        park (string/capitalize (name (deref (:park data/LastQuery))))]
    (println (string/join " " (rules (list (symbol activity) availability park))))))