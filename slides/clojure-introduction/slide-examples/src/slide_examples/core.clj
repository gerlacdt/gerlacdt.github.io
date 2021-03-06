(ns slide-examples.core)

;; this is a comment

;; creates a global var
(def x 1)

(def hello-1 (fn []
               "Hello World!"))

(def hello-2 #(str "Hello " %))

;; creates a global var with a function as value
(defn hello-3
  "Returns hello world"
  []
  "Hello World!")

;; (macroexpand-1 '(defn hello-x [] "hello-x"))

(defn hello-4
  "Multi arity function."
  ([] "Hello world!")
  ([name] (str "Hello " name "!")))

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
(assoc-in users [1 :age] 100)
(update-in users [1 :age] (partial + 100))

;; --> java calendar slide


;; more functions

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
  ([] "Hello with with default argument")
  ([language]
   (cond (= language :french) "Bonjour"
         (= language :spanish) "Hola"
         :else "Hello")))

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

(defn sum-up
  "Returns sum of all numbers from 0 to n"
  [n]
  (loop [i n
         sum 0]
    (if (zero? i)
      sum
      (recur (dec i) (+ sum i)))))

(defn print-list
  "Prints all entries of given list"
  [collection]
  (doseq [entry collection]
    (println entry)))


;; lazy data structures
(def natural-numbers (iterate inc 1))
;; (take 5 natural-numbers)

;; (inc (inc (inc 1)))
;; (-> 1 inc inc inc)


;; functional programming

;; map filter reduce
(def even-numbers (filter even? natural-numbers))

(defn sum-of-squares
  "Returns the sum of the given numbers squared."
  [& numbers]
  (reduce + 0
          (map (fn [x]
                 (* x x))
               numbers)))

(defn square
  "Returns the square of the given number."
  [x]
  (* x x))

;; map filter reduce
(defn sum-of-even-squares
  "Returns the sum of even squares of the given numbers"
  [& numbers]
  (->> numbers
       (map #(* % %))
       (filter even?)
       (reduce + 0)))

;; partial
(def even-numbers-2 (iterate (partial + 2) 0))
;; (take 10 even-numbers-2)

(def fifth (comp first rest rest rest rest))

(defn fnth
  "Returns a function which returns n-th item from given sequence."
  [n]
  (apply comp (cons first (take (dec n) (repeat rest)))))

;; ((fnth 5) [1 2 3 4 5 6])


(defn fib [n]
  (condp = n
    0 1
    1 1
    (+ (fib (dec n)) (fib (- n 2)))))

(def memo-fib
  (memoize (fn [n]
             (condp = n
               0 1
               1 1
               (+ (memo-fib (dec n)) (memo-fib (- n 2)))))))


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


;; pre- and post conditions
;; design-by-contract
(defn constrained-sqr [x]
  {:pre  [(pos? x)]
   :post [(> % 16), (< % 225)]}
  (* x x))


;; --> macro introduction slides

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

(defmacro unless-1
  "Inverted if"
  [test & branches]
  (conj branches (list 'not test) 'if))

;; (unless-1 (= 1 1)
;;         true
;;         false)

(defmacro unless-2
  "Inverted if, easier"
  [test & branches]
  `(if (not ~test)
     ~@branches))

;; (macroexpand-1 '(unless (= 1 0) "true branch" "false branch"))
;; (if (not (= 1 0))
;;   "true branch"
;;   "false branch")

(defmacro my-and
  ""
  ([] true)
  ([x] x)
  ([x & next]
   `(let [and# ~x]
      (if and# (my-and ~@next) and#))))

;; (my-and true false)
;; (pprint (macroexpand-1 '(my-and true false)))
;; (use 'clojure.walk)
;; (pprint (macroexpand-all '(my-and (= 1 1) (= 2 2) (= 1 2))))
