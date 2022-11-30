(ns project-chatbot.process-output
  (:use [org.clojars.cognesence.matcher.core])
  (:require [clojure.string :as string]
            [project-chatbot.data-ops :as data]))

;; (def rules
;;   '((rule 1 (Bertramka riding true) => (Yes, you can ride a bike in Bertramka))
;;     (rule 2 (Bertramka riding false) => (No, you can not ride a bike in Bertramka))))

(def rules
  '((rule 1 (riding true) => (Yes, you can ride a bike in))
    (rule 2 (riding false) => (No, you can not ride a bike in))))

;; Use defmethod instead?
(defmatch apply-rule [facts]
  ((rule ?n ??antecedents => ??consequents)
   :=> (mfor* [(? antecedents) facts]
              (mout (? consequents)))))

(defn apply-all [rules facts]
  (reduce concat
          (map #(apply-rule % facts) rules)))

;; (defrecord Question [keywords])
;; (defn init-question []
;;   (Question. (ref '())))
;; (def question (init-question))

(defn get-correct-synonym [input_keywords]
  (if (= input_keywords '(bike))
    :biking))

(def synonyms
  '([biking bike]))

(defn find-matching-synonym [symbol]
  ;; (mfind ['[?correct ?given]])

  (mlet ['(??pre x ??post)
         '(mango melon x apple pear berry)]
        (mout '(pre= ?pre post= ??post))))


(defn humanize-string [string]
  (case string
    true "Yes, "
    false "No, "))

(defn string-helper-boolean [boolean]
  (case boolean
    true ""
    false "not"))

(defn process-final-output [keywords]
  (mlet ['(?park ?poi ?value) keywords]
        ;; WIP, check if ?value is boolean, either here or outside. Replace ?value by "Yes"/"No"
        (mout '((humanize-string ?value) you can (string-helper-boolean ?value) ?poi in ?park))))

(defn get-park-name []
  (eval :Bertramka))

(defn get-poi []
  :riding)

(defn get-poi-value []
  true)

(defn construct-fact []
  (get-park-name) (get-poi) (get-poi-value))

(defn process_output_string [string]
  (println "Start")
  ;; (println get-park-name)
  ;; (println construct-fact)
  (println string)
    ;; (println (apply-rule
    ;;           '(rule 0 (Bertramka ride) => (Yes, you can ride a bike in Bertramka))
    ;;           all_keywords))
    ;; (println (get-correct-synonym string))

    ;; (println data/park-bertramka-data)
    ;; (println (get data/park-bertramka-data (get-correct-synonym string)))


    ;; (println (process-final-output '(Bertramka riding true)))
  ;; (println ((get-park-name) (get-poi) (get-poi-value)))

  (println (apply-all rules (list (list (symbol (deref (:park data/LastQuery))) (symbol (deref (:activity data/LastQuery))) :true))))

  ;; (println (list (list (symbol (deref (:park data/LastQuery))) (symbol (deref (:activity data/LastQuery))) :true)))



  (println (list (list (symbol (deref (:activity data/LastQuery))) :true)))
  (println (apply-rule
            '(rule 0 (riding :true) => (Yes, you can ride a bike in))
            (list (list (symbol (deref (:activity data/LastQuery))) :true))) (str (deref (:park data/LastQuery))))

  (println "End..."))



















    ;; (println (apply-rule
    ;;           '(rule 0 (Bertramka ride) => (Yes, you can ride a bike in Bertramka))
    ;;           (deref (:keywords question))))