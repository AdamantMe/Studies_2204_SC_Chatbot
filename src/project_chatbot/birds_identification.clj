(ns project-chatbot.birds-identification
  (:require [clojure.string :as str]))

;; Bird Data
(def dictionaries
  [{:name "House sparrow", :color "brown", :size "small", :tail "rounded"}
   {:name "Eurasian blue tit", :color "blue", :size "small", :tail "pointed"}
   {:name "Common chaffinch", :color "brown", :size "medium", :tail "forked"}
   {:name "Common blackbird", :color "black", :size "medium", :tail "rounded"}
   {:name "Great tit", :color "gray", :size "small", :tail "pointed"}
   {:name "Eurasian magpie", :color "black-white", :size "large", :tail "squared"}
   {:name "Eurasian nuthatch", :color "blue-gray", :size "small", :tail "pointed"}
   {:name "White stork", :color "white", :size "large", :tail "rounded"}
   {:name "European robin", :color "orange-red", :size "small", :tail "rounded"}
   {:name "European goldfinch", :color "yellow", :size "small", :tail "forked"}
   {:name "European greenfinch", :color "green", :size "small", :tail "forked"}
   {:name "European starling", :color "black", :size "medium", :tail "squared"}
   {:name "Common buzzard", :color "brown", :size "large", :tail "rounded"}
   {:name "Common kestrel", :color "brown", :size "small", :tail "squared"}
   {:name "Eurasian jay", :color "blue-gray", :size "medium", :tail "squared"}])

;; Takes a vector of dictionaries and a feature as arguments and returns a vector of distinct values of the given feature from the dictionaries.
(defn unique-feature-values [dicts feature]
  (distinct
   (map feature dicts)))

;; Takes a feature and a value and returns a predicate for the feature
(defn feature-value-predicate [feature value]
  [(keyword (str (name feature) "=" value))
   (fn [dict]
     (= (feature dict) value))])

;; Creates an array of predicates for each of the features in the dictionaries argument
(def predicates
  (apply concat
         (for [feature '(:color :size :tail)]
           (for [value (unique-feature-values dictionaries feature)]
             (feature-value-predicate feature value)))))

;; Takes a dictionary and an array of predicates and applies each predicate to the dictionary, 
;; returning a new dictionary with the predicates as keys and the result of the predicates as values.
(defn apply-predicates-to-dictionary [dict predicates]
  (reduce (fn [dict pred]
            (assoc dict
                   (first pred)
                   ((second pred) dict)))
          dict
          predicates))

(def dicts+
  (map (fn [dict] (apply-predicates-to-dictionary dict predicates)) dictionaries))
(def predicate-kws (map first predicates))

;; Takes a set of dictionaries and a feature, and returns an array of two sub-sets of the dictionaries, 
;; based on whether the feature is present or not in each dictionary.
(defn split-dicts [dicts feature]
  [(filter (fn [dict] (feature dict)) dicts),
   (filter (fn [dict] (not (feature dict))) dicts)])

;; Takes a set of dictionaries and an array of predicates, and returns a sorted array of predicates and their ratios
(defn get-predicate-ratios [dicts predicates-kws]
  (sort
   (fn [a b] (< (second a) (second b)))
   (filter (fn [x] (and (> (nth x 2) 0)
                        (> (nth x 3) 0)))
           (map (fn [pred]
                  (let [split (split-dicts dicts pred)
                        one (first split)
                        two (second split)
                        onec (count one)
                        twoc (count two)
                        ratio (if (> (min onec twoc) 0)
                                (/ (max onec twoc)
                                   (min onec twoc))
                                0)]
                    [pred ratio onec twoc]))
                predicates-kws))))

;; Takes a set of dictionaries, an answer, and an array of predicates.
;; Builds a decision tree based on the predicates and their ratios.
(defn build-dtree [dicts answer predicates-kws]
  (let [predicate-ratios (get-predicate-ratios dicts predicates-kws)]
    (cond
      (= (count dicts) 1) (let [dict (first dicts)]
                            {:resolution (str "According to my database, it is most likely a " (:name dict) "."),
                             :answer answer})
      :else (let [spred (first (first predicate-ratios))
                  split (split-dicts dicts spred)
                  one (first split)
                  two (second split)]
              {:answer answer,
               :question (str
                          "Is its "
                          (clojure.string/join " " (clojure.string/split (name spred) #"="))
                          "?"),
               :children [(build-dtree one "yes" (map first (rest predicate-ratios)))
                          (build-dtree two "no" (map first (rest predicate-ratios)))]}))))

;; Takes a decision tree node, and evaluates the node.
;; If the node has a resolution, it prints it. 
;; Otherwise, it keeps going through the decision tree until the end.
(defn dtree-evaluate [node]
  (if (:resolution node)
    (println (:resolution node))
    (do
      (println (:question node))
      (let [answer (str/lower-case (read-line))
            child (first (filter (fn [c] (= (:answer c) answer)) (:children node)))]
        (if child
          (dtree-evaluate child)
          (do
            (println "Unknown answer.")
            (dtree-evaluate node)))))))

(def decision-tree
  (build-dtree dicts+ nil predicate-kws))

(defn init-identify-bird []
  (println "A series of \"Yes\" & \"No\" questions to help identify a bird you're interested in:")
  (dtree-evaluate decision-tree))