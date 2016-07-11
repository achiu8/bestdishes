(ns bestdishes.views.reviews
  (:require [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]
            [clojure.string :as str]))

(defn display-reviews [reviews]
  [:div
   [:h4 "Reviews"]
   (map (fn [review]
          [:p (h (str/join " - " [(:rating review) (:content review)]))])
        reviews)])

(defn review-form [dish_id]
  [:div {:id "review-form"}
   [:h4 "Add a Review"
    (form/form-to [:post "/reviews"]
                  (anti-forgery/anti-forgery-field)
                  (form/hidden-field :dish_id dish_id)
                  (form/drop-down :rating (range 1 11))
                  (form/text-area :content)
                  (form/submit-button "Save"))]])
