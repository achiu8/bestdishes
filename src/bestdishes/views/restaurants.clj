(ns bestdishes.views.restaurants
  (:require [hiccup.core :refer [h]]
            [bestdishes.views.layout :as layout]
            [bestdishes.views.dishes :as dishes]))

(defn display-restaurant [restaurant]
  [:div
   [:h3 (h (:name restaurant))]
   (dishes/display-dishes (:dishes restaurant))])

(defn show [restaurant]
  (layout/common "bestdishes"
                 (display-restaurant restaurant)))
