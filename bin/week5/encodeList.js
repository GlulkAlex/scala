"use strict";
/*val consecList = List("a", "a", "a", "b", "c", "c", "a")

consecList.head = 'a' ++ consecList.tail = "a", "a", "b", "c", "c", "a"
consecList.head ++ 
	consecList.tail.head = 
		'a', 'a' ++ consecList.tail.head.tail = "a", "b", "c", "c", "a"
consecList.head ++ 
	consecList.tail.head ++ 
		consecList.tail.head.tail
			'a', 'a', 'a' ++ consecList.tail.head.tail.head.tail = "b", "c", "c", "a"		
			
"a" :: ("a" :: ("a" :: ("b" :: ("c" :: ("c" :: ("a" :: Nil))))))	

if (x == xs.head) {
	return ;
}	*/	

var 
	consecList = [
		"a", "a", "a", "a", "b", "c", "c", "c", "d", "a", "a"
		];
	
//function encodeArray(someArray, resultingArray=[]) {
	/* 'default parameters' is only available in ES6 (use esnext option).*/
function encodeArray(someArray, 
	resultingArray, 
	currentMatch, 
	counter) {
		if (!resultingArray) {
			resultingArray = [];
		}
		if (!counter) {
			counter = 1;
		}
		
		//Sequence
		function checkRepeat(index) {
			if (currentMatch) {//exist / defined
				if (someArray[index] == currentMatch) {
					//keep counting
					counter += 1;
				} else {
					resultingArray[resultingArray.length] = 
						"(" + currentMatch + ", " + counter + ")";
					
					currentMatch = someArray[index];//undefined;//reset
					counter = 1;//reset
				}
			} else {//not exist / undefined
				//initialize
				currentMatch = someArray[index];
				
				//new elem ?
				/*resultingArray[resultingArray.length] = 
					"(" + someArray[index] + ", " + 1 + ")";*/
			}
		}
		
		/* may be it must be tail recursive */
		function iter(index) {
			if (someArray[index]) {//exist
				//*console.log('index = ' + index);
				checkRepeat(index);
				/*if (index < someArray.lenght) {
					console.log('index incrementation');
				}*/
			} else {
				//*console.log('index out of bound');
				return ;
			}
			
			iter(index + 1);//tail recursion
		}
		
		iter(0);//first step / initialization
		return resultingArray/*Array*/;
}//*work as expected	