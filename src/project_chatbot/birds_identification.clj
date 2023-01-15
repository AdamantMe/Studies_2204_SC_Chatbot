(ns project-chatbot.birds-identification)

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

(defn build-dtree [])

(defn dtree-evaluate [])

(def the-dtree
  (build-dtree))

(defn init-identify-bird []
  (dtree-evaluate))