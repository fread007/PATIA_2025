(define (domain hanoi)
  (:requirements :strips :typing)
  (:types truc)
  (:predicates  (on ?x - truc ?y - truc)
                (small ?x - truc ?y - truc)
                (clearrr ?x - truc)
                )
  (:action move
    :parameters (?x - truc ?y - truc ?z - truc)
    :precondition (and (on ?x ?y) (clearrr ?x) (clearrr ?z) (small ?z ?x))
    :effect 
    (and 
      (not (clearrr ?z))
      (not (on ?x ?y))
      (clearrr ?y)
      (on ?x ?z)))
)