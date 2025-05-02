(define (domain sokoban)
  (:requirements :strips :typing)
  (:types lieu)
  (:predicates  (Pon ?x - lieu)
                (Con ?x - lieu)
                (isG ?x - lieu)
                (clear ?x - lieu)
                (connectU ?x - lieu ?y - lieu)
                (connectD ?x - lieu ?y - lieu)
                (connectL ?x - lieu ?y - lieu)
                (connectR ?x - lieu ?y - lieu)
                )
  (:action moveU
    :parameters (?x - lieu ?y - lieu)
    :precondition (and (Pon ?x) (clear ?y) (connectU ?x ?y))
    :effect 
    (and 
      (not (Pon ?x))
      (Pon ?y)))

    (:action moveD
    :parameters (?x - lieu ?y - lieu)
    :precondition (and (Pon ?x) (clear ?y) (connectD ?x ?y))
    :effect 
    (and 
      (not (Pon ?x))
      (Pon ?y)))
    
    (:action moveR
    :parameters (?x - lieu ?y - lieu)
    :precondition (and (Pon ?x) (clear ?y) (connectR ?x ?y))
    :effect 
    (and 
      (not (Pon ?x))
      (Pon ?y)))

    (:action moveL
    :parameters (?x - lieu ?y - lieu)
    :precondition (and (Pon ?x) (clear ?y) (connectL ?x ?y))
    :effect 
    (and 
      (not (Pon ?x))
      (Pon ?y)))

    (:action pushU
        :parameters (?x - lieu ?y - lieu ?z -lieu)
        :precondition (and 
            (Pon ?x)
            (Con ?y)
            (clear ?z)
            (connectU ?x ?y)
            (connectU ?y ?z)
        )
        :effect (and 
            (not (Pon ?x))
            (not (Con ?y))
            (Pon ?y)
            (Con ?z)
        )
    )

    (:action pushD
        :parameters (?x - lieu ?y - lieu ?z -lieu)
        :precondition (and 
            (Pon ?x)
            (Con ?y)
            (clear ?z)
            (connectD ?x ?y)
            (connectD ?y ?z)
        )
        :effect (and 
            (not (Pon ?x))
            (not (Con ?y))
            (Pon ?y)
            (Con ?z)
        )
    )

    (:action pushL
        :parameters (?x - lieu ?y - lieu ?z -lieu)
        :precondition (and 
            (Pon ?x)
            (Con ?y)
            (clear ?z)
            (connectL ?x ?y)
            (connectL ?y ?z)
        )
        :effect (and 
            (not (Pon ?x))
            (not (Con ?y))
            (Pon ?y)
            (Con ?z)
        )
    )

    (:action pushR
        :parameters (?x - lieu ?y - lieu ?z -lieu)
        :precondition (and 
            (Pon ?x)
            (Con ?y)
            (clear ?z)
            (connectR ?x ?y)
            (connectR ?y ?z)
        )
        :effect (and 
            (not (Pon ?x))
            (not (Con ?y))
            (Pon ?y)
            (Con ?z)
        )
    )


)