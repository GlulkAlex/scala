package week7

object waterPouringProblem {
  /*Assumed:
	we have 2 containers of different valume
	Goal:
	produce exact amount of liquid / water
	Avalable operations:
	pour water to containers until they full from pipe / hose or
	pour water from one of the containers until one of them is
	empty or full of water also
	each container may be emptied from water if any / it be in there*/
  /*in general:
	amount of containers may vary as they valume & final output value*/
  /*Model*/
  /*States & Moves ( of finite automaton ):
	Glass: Int
	/*number of deceliters in glass*/
	Sate: Vector[Int]( on every entry per glass )
	Moves:
	 Empty(glass)
	 Fill(glass)
	 Pour(from, to)
	 */
  /*let 'Path' be all possible combination of move sequences
	initial / starting state for 4dL & 9dL is (0, 0)
	final state (xdL, 6dL) or
	search space exhausted thus
	there is no solution*/
  class Pouring( capacity: Vector[ Int ] ) {
    //States:
    type State = Vector[ Int ]
    val initialState = capacity map ( _ => 0 )
    //Moves:
    /*case class*/
    trait Move {
      /*that track how each move change it state*/
      def change( state: State ): State
    }
    /*implementing trait method in each case calass*/
    case class Empty( glass: Int ) extends Move {
      /*old state updated with new one*/
      def change( state: State ): State = state updated ( glass, 0 )
    }
    case class Fill( glass: Int ) extends Move {
      /*to full capacity*/
      def change( state: State ): State = state updated ( glass, capacity( glass ) )
    }
    case class Pour( from: Int, to: Int ) extends Move {
      /*depends on comparing capacites of 'from' & 'to'*/
      def change( state: State ): State = {
        /*capacity( to ) - state( to ) is
        free avalible space in 'to'*/
        val amount = state( from ) min ( capacity( to ) - state( to ) )

        state
          .updated( from, state( from ) - amount )
          .updated( to, state( to ) + amount )
      }
    }

    /*auxiliary*/
    val glasses = 0 until capacity.length

    val moves = (
      ( for ( g <- glasses ) yield Empty( g ) ) ++
      ( for ( g <- glasses ) yield Fill( g ) ) ++
      ( for ( from <- glasses; to <- glasses if from != to ) yield Pour( from, to ) )
    )
    //Paths:
    /*history reversed
    so, last move comes first*/
    /*'endState' refactoring
    'val' for avalability outside of class*/
    class Path( history: List[ Move ],
                val endState: State ) {
      /*'endState' is expensive method &
      used frequently*/
      /*where it is lead to*/
      def endState3: State = trackState( history )
      /*auxiliary*/
      private def trackState( xs: List[ Move ] ): State = xs match {
        case Nil        => initialState
        /*last move comes first*/
        case move :: ys => move change trackState( ys )
      }
      /*or using 'foldRight'*/
      def endState2: State = ( history foldRight initialState )( _ change _ )

      /* 'endState2-3' version
      def extend( move: Move ): Path = new Path( move :: history )*/
      def extend( move: Move ): Path = new Path( move :: history,
        move change endState )
      override def toString = ( history.reverse mkString "&" ) + "->>" + endState
    }
    /*with empty history*/
    /* for 'endState2-3' version
    val initialPath = new Path( Nil )*/
    val initialPath = new Path( Nil, initialState)

    /*like an infinite integers / natural numbers stream*/
    def from( paths: Set[ Path ],
              explored: Set[ State ] ): Stream[ Set[ Path ] ] =
      /*boundary condition*/
      if ( paths.isEmpty ) {
        Stream.Empty
      } else {
        /*generate all posible paths wit path as prefix*/
        val more = for {
          path <- paths
          next <- moves map path.extend
          /*restriction for efficientcy*/
          if !( explored contains ( next.endState ) )
        } yield next /*Set*/
        /*& next iteration from 'more'*/
        paths #:: from( more, explored ++ ( more map ( _.endState ) ) )
      }
    /*Set of all possible paths*/
    val pathSets = from( Set( initialPath ), Set( initialState ) )

    /*takes valume in dL
      returns: all solution paths ordered by its length*/
    def solution( target: Int ): Stream[ Path ] =
      for {
        pathSet <- pathSets
        path <- pathSet
        if path.endState contains ( target )
      } yield path /*Stream*/
  }

  val problem1 = new Pouring( Vector( 4, 9 ) )    //> problem1  : week7.waterPouringProblem.Pouring = week7.waterPouringProblem$P
                                                  //| ouring@17c74e5
  val problem2 = new Pouring( Vector( 4, 7, 9 ) ) //> problem2  : week7.waterPouringProblem.Pouring = week7.waterPouringProblem$P
                                                  //| ouring@61f5df

  problem1.moves                                  //> res0: scala.collection.immutable.IndexedSeq[Product with Serializable with 
                                                  //| week7.waterPouringProblem.problem1.Move] = Vector(Empty(0), Empty(1), Fill(
                                                  //| 0), Fill(1), Pour(0,1), Pour(1,0))
  problem2.moves                                  //> res1: scala.collection.immutable.IndexedSeq[Product with Serializable with 
                                                  //| week7.waterPouringProblem.problem2.Move] = Vector(Empty(0), Empty(1), Empty
                                                  //| (2), Fill(0), Fill(1), Fill(2), Pour(0,1), Pour(0,2), Pour(1,0), Pour(1,2),
                                                  //|  Pour(2,0), Pour(2,1))
  problem1.pathSets                               //> res2: Stream[Set[week7.waterPouringProblem.problem1.Path]] = Stream(Set(->>
                                                  //| Vector(0, 0)), ?)
  problem1.pathSets.take( 1 ).toList              //> res3: List[Set[week7.waterPouringProblem.problem1.Path]] = List(Set(->>Vect
                                                  //| or(0, 0)))
  problem1.solution( 6 )                          //> res4: Stream[week7.waterPouringProblem.problem1.Path] = Stream(Fill(1)&Pour
                                                  //| (1,0)&Empty(0)&Pour(1,0)&Empty(0)&Pour(1,0)&Fill(1)&Pour(1,0)->>Vector(4, 6
                                                  //| ), ?)
  /*not efficient because
  in each state generats an old states
  those are revisited again
  */
  problem1.solution( 3 )                          //> res5: Stream[week7.waterPouringProblem.problem1.Path] = Stream(Fill(0)&Pour
                                                  //| (0,1)&Fill(0)&Pour(0,1)&Fill(0)&Pour(0,1)->>Vector(3, 9), ?)
  /*no solution ?*/
  problem2.solution( 17 )                         //> res6: Stream[week7.waterPouringProblem.problem2.Path] = Stream()
}