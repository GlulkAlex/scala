"using strict";
/* for invariant type 'T' 
 * for covariant 'T' */
var 
	Nil,//{}
	Empty;//{}

//implementation
//'cons' stands for construction operation
function Cons1(
	listHead, 
	listTail) {
	//head, tail - are fields 
	//cause it never empty

	//warning: Bad assignment
	//this = new List(); //trying to inherited from 'List'
	
	this.isEmpty = false;
	this.head = listHead;//may be an object
	this.tail = listTail;
	//may be better override 'toString' method
	this.representation = "[h:" +this.head + "|t:" + 
		this.tail.representation + "]";
	
	//have implemented in 'List' already	
	//not DRY
	this.prepend = function(elem){ 
		return new Cons1(
			elem, 
			this);
	};
	
	/*function stringRepresentation() {
		return "[h:" + head + "|t:" + tail + "]";
	}*/
	
	return this;//?or new 'List'
}		

function Cons(
	listHead, 
	listTail) {

		var 
			currList = new List(); //trying to inherited from 'List'
		
		currList.isEmpty = false;
		currList.head = listHead;//may be an object
		currList.tail = listTail;
		//may be better override 'toString' method
		currList.representation = "[h:" +currList.head + "|t:" + 
			currList.tail.representation + "]";
		
		//have implemented in 'List' already	
		
		return currList;
}		

//* class prototype / constructor
function List() {
	var
		isEmpty,//Boolean
		head,//T
		tail;//List[T]	
	
	return {
		isEmpty: isEmpty,
		//for non empty list
		head: head,
		//for remaining list
		tail: tail,
		//add new elem at the beginning of the list		
			//then add current list at the end as 'tail'
			//so, method creates a new list from 'elem' and 'this'
		prepend: function(elem){ 
			return new Cons(
				elem, 
				this);
		}/*,
		presentation: stringRepresentation()*/		
	};
}

/**
 * 'Nil' is a single instance needed or singleton 
 */
Nil = new List(); 
	Nil.isEmpty = true;
	//Can not take a head of Nil 
	//def head = throw new NoSuchElementException("Nil.head")
	Nil.head = console.log("Nil.head");
	//def tail: Nothing = throw new NoSuchElementException("Nil.tail")
	Nil.tail = console.log("Nil.tail");	
	//*Nil.prepend //* inherited from 'List'
	//visual representation of tree node
	Nil.representation = "Nil";	

//*extends 'IntSet'
//*singleton
//function Empty()  {
Empty = {
	contains: function contains(x){ 
		return false;
		},
	incl: function incl(x) {
		//return new NonEmpty(x, new Empty, new Empty)
		return new NonEmpty(x, Empty, Empty);
	},
	union: function union(other) {return other;},
	representation: "."
};

//extends 'IntSet'	
function NonEmpty(
	elem, 
	left, 
	right)  {
		
		return {
			contains: function contains(x) {
				if (x < elem) {
					return left.contains(x);}
				else if (x > elem) {
					return right.contains(x);}
				else {
					return true;}
			},
				
			incl: function incl(x) {
				if (x < elem) {
					return new NonEmpty(
						elem, 
						left.incl(x), 
						right);}
				else if (x > elem) {
					return new NonEmpty(
						elem, 
						left, 
						right.incl(x));}
				else {
					return this;}
			},
				
			union: function union(other) {
				return ((this.left.union(right)).union(other))
					.incl(elem);
			},
			
			representation: "{" + left + elem + right + "}"
		};
  }
	
/*objects test */
  /*var 
		f(xs: List[NonEmpty], x: Empty) = xs prepend x*/
		
var x = Nil;
var xs = new List();
xs.prepend(Empty);//?

//xs = xs.prepend(Empty);
console.log("x is: " + x.representation);
console.log("xs is: " + xs.representation);
/*console.log("xs prepend x is: " +           
	new Cons(
		new NonEmpty(
			1, 
			//new Empty(), //TypeError: object is not a function
			Empty, 
			Empty), 
		Nil)
			.prepend(Empty)//TypeError: undefined is not a function
			.representation
);*/

//Generic functions
//that is example of 'Generics' (form of) in polymorphism
//mostly from functional languages 
  //if 'Nil' is a class
  //def singleton[T](elem: T) = new Cons[T](elem, new Nil[T]) 
  //if 'Nil' is an object
  function singleton(elem) {return new Cons(elem, Nil);}

  singleton(1);
  var 
		s1 = singleton(true);

  //or
  //compiler deduction infers right type from function parameter
  //as they have the same type both  
  var 
		s2 = singleton(1);
  //in general type parameter do not store anywhere in the program
  //they have meaning for compiler
  //for type correctness validation

  //List(1, 2, 3)
  //if 'Nil' is a class
  //val l1 = new Cons(1, new Cons(2, new Cons(3, new Nil)))
  var 
		l1 = new Cons(1, new Cons(2, new Cons(3, Nil)));
  //List(true, false)
  //if 'Nil' is a class
  //val l2 = new Cons(true, new Cons(false, new Nil))
  var 
		l2 = new Cons(true, new Cons(false, Nil));
  //List(List(true, false), List(3))
  //in current trait definition T is invariant -- never changing
  //use +T instead of T ?
  //if 'Nil' is a class
  //val l3 = new Cons[Any](l2, new Cons(3, new Nil))
  var 
		l3 = new Cons(l2, new Cons(3, Nil));

  // TODO Write a function nth 
  /* that takes 
   * an integer 'n' and 
   * a list and 
   * selects the n'th element of the list.
   * Elements are numbered from 0.
   * If index is 
   * outside the range 
   * from (0 up to the length of the list minus one), 
   * a 'IndexOutOfBoundsException' should be thrown.
   */
  /**
   * 'n' as index - point to 'head' of list
   * 'nth' return type is 'T'
   */
  function nth(n, li) {
    //short circuit to the most important condition
    //so actually full length of the initial list is unknown
    //only the properties of passing parameters matters 
    //functions runs until n !== 0 and
    //stops when either
    // 'n' became == 0 -- desired result or
    // l.tail is Nil -- exception
    if (li.isEmpty || n < 0) {
      //substituted / evaluated values are before exception, so it did no good 
      //throw new IndexOutOfBoundsException("Index " + n + " is outside the range of list " + l)
      //*throw new IndexOutOfBoundsException("Index is outside the range of list")
      return console.log("Index is outside the range of list");
    } else if (n == 0) {
      //Executes when the Boolean expression 1 is true
      return li.head; //return value
      /*} else if(Boolean_expression 2){
       //Executes when the Boolean expression 2 is true
    } else if(Boolean_expression 3){
       //Executes when the Boolean expression 3 is true
        */
    } else {
      //Executes when the none of the above condition is true.
      //recursion / iteration
      return nth(n - 1, li.tail);
    }
  }

  //expected exception: NoSuchElementException("Nil.tail")
  //nth(-1, l1)

  /** Unit test */
    console.log("0 element of List(1, 2, 3) is: " + nth(0, l1))
    console.log("2 element of List(1, 2, 3) is: " + nth(2, l1))
    //console.log("5 element of List(1, 2, 3) is: " + nth(5, l1))
    console.log("List(1, 2, 3) is: " + l1.representation)
    console.log("List(true, false) is: " + l2.representation)
    console.log("List(List(true, false), List(3)) is: " + l3.representation)
    console.log("singleton(true) is: " + s1.representation)
    console.log("singleton(1) is: " + s2.representation)
    //console.log("-3 element of List(1, 2, 3) is: " + nth(-3, l1))
  } //> main: (args: Array[String])Unit

  // TODO Define an object List{...} with 
  /* 3 functions in it 
   * so that 
   * users can 
   * create lists of length 0-2 using syntax
   */
  //List() //the empty list
  //List(1) //the list with single element 1.
  //List(2, 3) // the list with elements 2 and 3
  /* if 'Nil' is a class
  def f(): List[Nothing] = new Nil
  def f1[T](elem: T): List[T] = new Cons(elem, new Nil)
  def f2[T](elem1: T, elem2: T): List[T] = new Cons(elem1, new Cons(elem2, new Nil))*/
  function f() {return Nil;}
  function f1(elem) {return new Cons(elem, Nil);}
  function f2(elem1, elem2) {return new Cons(elem1, new Cons(elem2, Nil));}

  /** Unit test */
    console.log("List() the empty list is: " + f)
    console.log("List(1) the list with single element 1 is: " + f1(1))
    console.log("List(2, 3) the list with elements 2 and 3 is: " + f2(2, 3))
  }

/**
 * Functions and Methods Implementation
 * by Martin Odersky
 */
/*object ListMO {
  /*if 'Nil' is a class
  def apply[T]() = new Nil
  def apply[T](x: T): List[T] = new Cons(x, new Nil)
  //List(2, 3) <=> List.apply(2, 3)
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))*/
	
  /*def apply[T]() = Nil
  def apply[T](x: T): List[T] = new Cons(x, Nil)
  //List(2, 3) <=> List.apply(2, 3)
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, Nil))

    console.log("MO List() the empty list is: " + apply)
    console.log("MO List(1) the list with single element 1 is: " + apply(1))
    console.log("MO List(2, 3) the list with elements 2 and 3 is: " + apply(2, 3))

}*/

/**
 * Functions and Methods Implementation
 * by Martin Odersky
 */
function insert(x, xs) {
	if (xs == Nil) {
	//insert an element to an empty list
	//so, elem is '.head'
	//complexity proportional to
	// N
		console.log('Nil.prepend(' + x + ')');
		return Nil.prepend(x); 
	} else {	
	//complexity proportional to
	// N * N
		if ( x <= xs.head ) {
			console.log(xs.representation + '.prepend(' + x + ')');
			return xs.prepend(x); 
		} else {
			console.log('insert reccursion with (' + 
				x + ',' + xs.tail.representation + ',' + xs.head + ')');
			return insert( 
				x, 
				xs.tail )
					.prepend(xs.head);
		}
	}     
}

function insertionSort(xs) {
	if (xs == Nil) {
		return Nil;
	} else {	
		//list pattern
		/*case y :: ys =>*/ 
		console.log('initialization / first run of insert with (' + 
			xs.head + ', reccursive insertionSort(' + xs.tail.representation + '))');
		return insert(
			xs.head, 
			insertionSort(xs.tail));
	}
}

var 
	nums, //= 0 :: (1 :: (2 :: (3 :: Nil)))
	li7392;

nums = Nil
	.prepend(3)
		.prepend(2)
			.prepend(1)
				.prepend(0);
					
li7392 = Nil					
	.prepend(2)
		.prepend(9)
			.prepend(3)
				.prepend(7);
					
console.log("List(0, 1, 2, 3) is: " + nums + nums.representation);
console.log("empty List is: " + Nil);
console.log("empty List is: " + new List());
console.log("0::1::2::3::Nil '.isEmpty' is: " + nums.tail.tail.tail.tail.isEmpty);
console.log("inSort() of 7::3::9::2::Nil is: " + insertionSort(li7392).representation);


//classPrototypeName.prototype.newPropertyDefenition
// This	creates a new object
//*var x = new myFunction("John","Doe");
//*obj.defineProperty(obj, "x", {value:0, writable:false});
/* Definition and Usage
The undefined property indicates that a variable has not been assigned a value.
var x;

if (x === undefined) {
    txt = "x is undefined";
}*/
function isArray(myArray) {
    return myArray
			.constructor
				.toString()
					.indexOf("Array") > -1;
					//.item(0) !== null
}