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


(defn build-dtree [])

(defn dtree-evaluate [])

(def the-dtree
  (build-dtree))

(defn init-identify-bird []
  (dtree-evaluate))