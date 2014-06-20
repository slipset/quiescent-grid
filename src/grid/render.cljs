(ns grid.render
  (:require [quiescent :as q :include-macros true]
            [quiescent.dom :as d]))


(q/defcomponent User
  [header data]
  (apply d/tr {}
        (map (fn [key] (d/td {} (get data key))) (map :key header))))

(defn render-header [header]
  (d/thead {}
           (apply d/tr {}
                 (map (fn [h] (d/th {:className (:className h)} (:title h))) header))))

(q/defcomponent Grid
  [meta data]
    (d/div {:className "panel panel-default"}
           (d/div {:className "panel-heading"} (str "SuperAmazing ClojureScript w/React grid"))
           (d/table {:className "table"}
                    (render-header (:header meta))
                    (apply d/tbody {}
                           (map (partial User (:header meta)) (:data data))))))

(def my-data {:data [{:name "John Doe"
                          :email "user@email.com"
                          :country "USA" }
                         {:name "Frank Bølviken"
                          :email "frank.bolviken@gmail.com"
                          :country "Norway"}]})

(def my-meta {:header [{:key :email :title "Email"
                        :className "email"
                        :onClick (fn [] (...))}
                       {:key :name :title "Name"}
                       {:key :country :title "Country"}]})
           
(q/render (Grid my-meta my-data)
          (.getElementById js/document "grid"))
