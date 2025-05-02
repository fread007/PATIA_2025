(define (problem hanoi-3-0)
  (:domain hanoi)
  (:objects 
    a b c t1 t2 t3 - truc)
  (:init 
    (clearrr c)
    (clearrr t2)
    (clearrr t3)
    (on c b)
    (on b a)
    (on a t1)
    (small a b) (small a c)  (small t1 a) (small t2 a) (small t3 a)
    (small b c) (small t1 b) (small t2 b) (small t3 b)
    (small t1 c) (small t2 c) (small t3 c))
  (:goal 
    (and 
      (clearrr c)
      (on c b)
      (on b a)
      (on a t3)))
)