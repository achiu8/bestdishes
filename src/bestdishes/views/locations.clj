(ns bestdishes.views.locations
  (:require [hiccup.core :refer [h]]
            [bestdishes.views.layout :as layout]
            [bestdishes.views.dishes :as dishes]))

(defn display-location [location]
  [:div
   [:h3 (h (:city location))]
   (dishes/display-dishes (:dishes location))])

(defn show [location]
  (layout/common (display-location location)))
