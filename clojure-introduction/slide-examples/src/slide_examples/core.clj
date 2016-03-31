(ns slide-examples.core)

;; this is a comment

;; creates a global var
(def x 1)

(def hello-1 (fn []
               "Hello World!"))

;; creates a global var with a function as value
(defn hello-2
  "Returns hello world"
  []
  "Hello World!")

(defn hello-3
  "Multi arity function."
  ([] "Hello world!")
  ([name] (str "Hello " name "!")))


(defn add-1
  "Sum up given parameters."
  [& more]
  (apply + more))

(defn destruct-1
  "Extract firstname and lastname and returns fullname."
  [{:keys [:firstname :lastname]}]
  {:fullname (str firstname " " lastname)})

(defn destruct-2
  "Extracts firstname and lastname but also add defaults if one of the
  names do not exist. Returns concatenated fullname string."
  [{:keys [:firstname :lastname]
    :or {:firstname "John" :lastname "Doe"}}]
  {:fullname (str firstname " " lastname)})

;; if and cond

(defn my-even?
  "Returns true if number is even."
  [n]
  (if (even? n)
    true
    false))

(defn greeting
  "Returns the greeting in the given language. Defaults to :english"
  ([language]
   (cond (= language :english) "Hello"
         (= language :french) "Bonjour"
         (= language :spanish) "Hola"
         :else "Hello"))
  ([] "Hello"))

;; let

(defn area-circle
  "Returns the area of a circle with the given radius"
  [radius]
  (let [pi 3.1415
        radius-squared (* radius radius)]
    (* pi radius-squared)))

;; loops with recur

(defn factorial
  "Returns the factorial of the given number."
  [n]
  (loop [i n
         acc 1]
    (if (zero? i)
      acc
      (recur (dec i) (* acc i)))))

(defn print-list
  "Prints all entries of given list"
  [collection]
  (doseq [entry collection]
    (println entry)))

;; data structures

(def a-vector [1 2 3])
(def another-vector (vector 1 2 3))
(conj a-vector 4)

(def a-list '(1 2 3))
(def another-list (list 1 2 3))
(conj a-list 4)

(def a-set #{1 2 3})
(def another-set (set (list 1 2 3 1)))
(conj a-set 4)

(def a-map {:foo "bar" :a 1})
(def another-map (hash-map :foo 1 :bar 2))
(assoc a-map :b 2)

;; (first a-vector)
;; (rest a-vector)
;; (drop 2 a-vector)
;; (pop a-vector)
;; (peek a-vector)
;; (into {:c "value"} a-map)
;; (into [5 6 7] a-vector)
;; (concat [1 2] [3 4])

(def users [{:name "James" :age 26}  {:name "John" :age 43}])
;; update the age of the second (index 1) user
(assoc-in users [1 :age] 44)



;; lazy data structures
(def natural-numbers (iterate inc 1))
;; (take 5 natural-numbers)

;; map filter reduce
(def even-numbers (filter even? natural-numbers))

(defn sum-of-squares
  "Returns the sum of the given numbers squared."
  [& numbers]
  (reduce + 0  (map (fn [x]
                      (* x x))
                    numbers)))

;; partial
(def even-numbers-2 (iterate (partial + 2) 0))
;; (take 10 even-numbers-2)

;; concurrency refs (in-memory transactions)
(def account-a (ref 1000))
(def account-b (ref 0))

(defn transfer-money
  "Transfers the given amount of money from account a to account b"
  [amount a b]
  (dosync
   (alter a #(- % amount))
   (alter b #(+ % amount))))
;; @account-a
;; @account-b
;; (transfer-money 100 account-a account-b)

;; for-each macro
(defmacro for-each
  "Simulates the java for-each loop"
  [[sym coll] & body]
  `(loop [coll# ~coll]
     (when-let [[~sym & xs#] (seq coll#)]
       ~@body
       (recur xs#))))

;; (for-each [x [1 2 3]]
;;           (println x))


;; infix macro
(defmacro infix
  "Use this macro to get a familiar syntax."
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))

;; (infix (1 + 2))
;; (macroexpand-1 '(infix (1 + 2)))

(defmacro unless
  "Inverted if"
  [test & branches]
  (conj branches (list 'not test) 'if))

;; (unless (= 1 1)
;;         true
;;         false)

;; (macroexpand-1 '(unless (= 1 1) true false))

(defmacro my-and
  ""
  ([] true)
  ([x] x)
  ([x & next]
   `(let [and# ~x]
      (if and# (my-and ~@next) and#))))

;; (my-and true false)
;; (pprint (macroexpand-1 '(my-and true false)))
