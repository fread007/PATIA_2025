(define (domain taquin)
  (:requirements :strips :typing)
  (:types piece lieu)
  (:predicates  (on ?x - piece ?y - lieu)
                (clear ?x - lieu)
                (connect ?x - lieu ?y - lieu)
                )
  (:action move
    :parameters (?x - piece ?y - lieu ?z - lieu)
    :precondition (and (on ?x ?y) (clear ?z) (connect ?y ?z))
    :effect 
    (and 
      (not (clear ?z))
      (not (on ?x ?y))
      (clear ?y)
      (on ?x ?z)))
)