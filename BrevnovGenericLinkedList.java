//Anatoly Brevnov
//Period 5
//Advanced Computer Science
//October 12, 2015
//Instructor: Darby Thompson

//Project Name: Singlely-Linked Lists




import java.lang.Math;


public class BrevnovGenericLinkedList{
    
    
    public static void main(String[] args){
        
        //here im initializing my list
        LinkedList<Integer> list=new LinkedList<Integer>();
        list.add(1);
        list.remove();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        
        list.swap(2,3);
        
        list.remove(0);
        list.set(1,1);
        System.out.print(list.toString());
       

        
        
    }
    
}




class LinkedList<T>{
    
    //setting up a class with 1 property and a constructor
    Node<T> head;
    Node<T> tail;
    
    LinkedList(){
        
        head=null;
        tail=null;
        
        
    }
    
    //bellow i define by abilities for a LinkedList
    
    public void clear(){
        //sets head to null which cuts off rest of list and essentially clears it
        
        this.head=null;
        this.tail=null;
    }
    
    public String toString(){
        String finalString="";
        finalString=finalString+"";
        finalString=finalString+"[";
        //while loop until .next is empty while printing
        boolean done=false;
        Node<T> test=this.head;
        if (test==null){
            done=true;
        }
        //System.out.println("test.next");
        
        while (done==false){
            if (test.next==null){
                done=true;
                finalString=finalString+test.value;
            }
            else {
                finalString=finalString+test.value+ ", ";
                
                
                test=test.next;
            }
        }
        
        finalString=finalString+"]";
        
        finalString=finalString+"";
        
        return finalString;
        
    }
    
    public void swap(int a, int b){
        
        //make a always less than b
        if (a>b){
            int l=a;
            a=b;
            b=l;
        }
        
        try {
            
            if (a==b){
                
                throw new Exception("Error: You are inputting two of the same positions");
            }
            
            if (a>this.size()-1||b>this.size()-1){
                
                throw new Exception("Error: Your inputs are out of bounds");
            }
            
            Node<T> alpha=getNode(a);
            Node<T> beta=getNode(b);
            Node<T> alphaPointer=alpha.next;
            Node<T> betaPointer=beta.next;
            Node<T> alphaBefore=alpha.previous;
            Node<T> betaBefore=beta.previous;
            
            
            if (a!=0&&b!=0&&b-a!=1){
                //make the Node T before alpha be the Node T before beta, beta is Node T before what used to be in front of alpha, what used to be before beta is now before alpha, what is beta is now alpha, what is in front of alpha used to be in front of beta
                //Node T betaBefore2=getNode(b-1);
                
                alpha.next=beta.next;
                beta.next=alphaPointer;
                betaBefore.next=alpha;
                alphaBefore.next=beta;
                
                beta.previous=alphaBefore;
                alphaPointer.previous=beta;
                alpha.previous=betaBefore;
                if (b<this.size()-1){
                    betaPointer.previous=alpha;
                }
                
                else {
                    
                    this.tail=alpha;
                }
                
                
            }
            //special cases occur if a is the first position or if b is adjacent a (the algorithm above will cause the list to link to itself, causing infinite looping while printing the list)
            
            
            else if (b-a==1){
                if (a!=0&&b!=0){
                    /*Node T alpha=getNode(a);
                     Node T beta=getNode(b);
                     //Node T alphaPointer=alpha.next;
                     Node T betaPointer=beta.next;
                     
                     Node T alphaBefore= getNode(a-1);
                     alphaBefore.next=beta;
                     */
                    //if one space apart
                    beta.next=alpha;
 
                    alpha.next=betaPointer;
                    alphaBefore.next=beta;
                    
                    beta.previous=alphaBefore;
                    //betaBefore.previous=beta;
                    //betaPointer.previous=
                    alpha.previous=beta;
                    if (b<this.size()-1){
                    betaPointer.previous=alpha;
                    }
                    
                    else {
                        
                        this.tail=alpha;
                    }
                    
                    //System.out.println("a");
                }
                else {
                    
                    // Node T alpha=getNode(a);
                    // Node T beta=getNode(b);
                    // //Node T alphaPointer=alpha.next;
                    // Node T betaPointer=beta.next;
                    //Node T betaBefore=getNode(b-1);
                    //if a is the first element in the list AND right next to each other
                    //System.out.println("asfasdfasdf");
                    beta.next=alpha;
                    alpha.next=betaPointer;
                    
                    alpha.previous=beta;
                    beta.previous=null;
                    if (b<this.size()-1){
                    betaPointer.previous=alpha;
                        
                    }
                    else {
                        this.tail=alpha;
                    }
                    this.head=beta;

                    
                    //System.out.println("a");
                    
                    
                }
                
                
                
                
                
            }
            else {
                
                // Node T alpha=getNode(a);
                // Node T beta=getNode(b);
                // Node T alphaPointer=alpha.next;
                // Node T betaPointer=beta.next;
                // Node T betaBefore=getNode(b-1);
                
                // if a is the first element of the list but a and b are not right next to eachother
                alpha.next=betaPointer;
                beta.next=alphaPointer;
                betaBefore.next=alpha;
                head=beta;
                
                beta.previous=null;
                alphaPointer.previous=beta;
                alpha.previous=betaBefore;
                if (b<this.size()-1){
                    betaPointer.previous=alpha;
                }
                
                else {
                    
                    this.tail=alpha;
                }
                
                
                
                
                
            }
            
        }
        
        catch(Exception e){
            if (a==b){
                //if you try to swap two of the same element
                System.out.println("You cannot swap two items of the same position! That makes no sense!");
            }
            else {
                System.out.println("Some position is out of bounds: cannot swap");
            }
            
            
        }
    }
    
    
    public void add(T value){
        //Logic: loop to last element of list, make that.next equal to a Node T with the given value
        /*Node T node=new Node(value);
         boolean done=false;
         Node T test=this.head;
         int count=0;
         if (test==null){
         done=true;
         this.head=node;
         }
         while (done==false){
         if (test.next==null){
         test=this.getNode(count);
         test.next=node;
         tail=test.next;
         node.previous=test;
         
         done=true;
         }
         else {
         
         test=test.next;
         
         count++;
         }
         }
         */
        try {
            Node<T> added=new Node<T>(value);
            
            if (head==null){
                
                head=added;
                tail=head;
            }
            else {
                //System.out.println(tail.value);
                Node<T> temp=tail;
                tail=added;
                tail.previous=temp;
                
                temp.next=tail;

                
            }
            
        }
        
        catch (Exception e){
            
            System.out.println("Paramater is not of valid type");
        }
        
    }
    
    
    
    
    
    
    
    
    public void print(){
        System.out.println("");
        System.out.print("[");
        //while loop until .next is empty while printing
        boolean done=false;
        Node<T> test=this.head;
        if (test==null){
            done=true;
        }
        //System.out.println("test.next");
        
        while (done==false){
            if (test.next==null){
                done=true;
                System.out.print(test.value);
            }
            else {
                System.out.print(test.value + ", ");
                
                
                test=test.next;
            }
        }
        
        System.out.print("]");
        
        System.out.println("");
        
        
    }
    
    public void reversePrint(){
        //System.out.println("");
        
        
        //while loop until .next is empty while printing
        // boolean done=false;
        // Node T test=head;
        // int count=0;
        // if (test==null){
        // done=true;
        // }
        // while (done==false){
        // if (test.next==null){
        // done=true;
        // count++;
        // }
        // else {
        
        // test=test.next;
        // count++;
        // }
        // }
        // //System.out.println(count);
        // int[] reverse=new int[count];
        // int count2=0;
        // test=this.head;
        // done=false;
        // if (test==null){
        // done=true;
        
        // }
        // while (done==false){
        // if (test.next==null){
        // done=true;
        // reverse[count2]=test.value;
        // count2++;
        
        // }
        // else {
        
        // reverse[count2]=test.value;
        // test=test.next;
        
        // count2++;
        
        // }
        // }
        
        
        //loop backwards using the size function to give you the size of the array and the get Node T function while printing
        /* System.out.print("[");
         
         for (int i=this.size()-1;i>=0;i--){
         if (i==0){
         System.out.print(this.getNode(i).value);
         }
         else {
         
         System.out.print(this.getNode(i).value + ", ");
         
         }
         
         
         }
         
         System.out.print("]");
         
         System.out.println("");
         
         */
        
        System.out.println("");
        System.out.print("[");
        //while loop until .next is empty while printing
        boolean done=false;
        Node<T> test=tail;
        if (test==null){
            done=true;
        }
        
        
        //System.out.println("test.next");
        
        while (done==false){
            if (test.previous==null){
                done=true;
                System.out.print(test.value);
            }
            else {
                System.out.print(test.value + ", ");
                
                
                test=test.previous;
            }
        }
        
        System.out.print("]");
        
        System.out.println("");
        
        
        
    }
    
    T remove(){
        
        T value=null;
        
        
        try{
            
            if (head==null){
                System.out.println("This list has no elements");
                
            }
            
            else if (head==tail){
                value=tail.value;
                
                this.clear();
            }
            else if (head.next==tail){
                value=tail.value;
                
                tail.previous=null;
                head.next=null;
                tail=head;
            }
            else {
                value=tail.value;
                
                
                tail=tail.previous;
                tail.next=null;
            }
            return value;
            
        }
        
        
        catch (Exception e){
            
            
            System.out.println("This list has no elements!!!");
            return null;
        }
        
        
        
        
        
        //return value;
        
    }
    
    
    public void add(int position,T value){
        /*
         Node T node=getNode(position);
         Node T node2=(value);
         if (position==0){
         node2.next=node;
         
         head=node2;
         }
         else {
         getNode(position-1).next=node2;
         //node3.next=node2;
         node2.next=node;
         
         
         
         }
         
         */
        
        /*
        
        Node T a = new Node(value);
        Node T b = head;
        
        if (position == 0){
            a.next=head;
            this.head = a;
        } else{
            for(int i=1;i<position;i++){
                
                b = b.next;
                
            }
            
            a.next=b.next;
            
            b.next=a;
            a.previous=b;
            
        }
        
        if (position==this.size()-1){
            
            tail=a;
        } */
        
        try {
            
            Node<T> nodeToAdd=new Node<T>(value);
            //System.out.println(atPosition.value);
            
            if (position==0&&position-1!=this.size()-1){
                Node<T> atPosition=this.getNode(position);

                nodeToAdd.next=atPosition;
                atPosition.previous=nodeToAdd;
                //nodeToAdd.previous=null;
                head=nodeToAdd;
                
            }
            
            else if (position-1==this.size()-1){
                
                this.add(value);
                //System.out.println("asfasf");
            }
            
            else {
                Node<T> atPosition=this.getNode(position);

                
                Node<T> nodeBefore=atPosition.previous;
                nodeBefore.next=nodeToAdd;
                nodeToAdd.previous=nodeBefore;
                nodeToAdd.next=atPosition;
                atPosition.previous=nodeToAdd;
                
            }
            
            
            
            
        }
        catch (Exception e){
            
            System.out.println(e);
        }
        
        
    }
    
    
    public boolean isEmpty(){
        
        //check if head is null, if true: empty, otherwise not empty
        
        if (head==null){
            
            return true;
        }
        
        else {
            
            return false;
        }
        
    }
    
    public int size(){
        //while loop until node.next is null while counting
        boolean done=false;
        int count=0;
        Node<T> node=head;
        
        if (node==null){
            done=true;
        }
        while (done==false){
            if (node.next==null){
                //node=node.next;
                count++;
                done=true;
                
            }
            else {
                
                node=node.next;
                count++;
            }
        }
        
        return count;
    }
    
    
    
    public T get(int place){
        try {
            //iterate through list until u hit the place and set a Node T to be that node.next and return the value of the node
            Node<T> myNode=head;
            int i;
            for (i=0;i<place;i++){
                myNode=myNode.next;
            }
            return myNode.value;
        }
        catch (Exception e){
            
            System.out.println("Error: position is too large, cannot get");
            return null;
            
            
        }
        
    }
    public void set(int position,T value){
        try {
            //get a Node T using the getNode() function and set its value to the given value
            Node<T> node  = getNode(position);
            node.value=value;
        }
        catch (Exception e){
            
            System.out.println("Your position is out of bounds, cannot set");
            
        }
    }
    public T remove(int place){
        
        try{
            Node<T>  oldNode=getNode(place);
            //special case if the first Node T (because no nodes before it), but otherwise taking Node T before the given place and making its .next equal to the Node T after the given place
            if (this.size()==1&&place==0){
                
                this.clear();
            }
            
            else if (place==0){
                if (oldNode==tail){
                    this.clear();
                }
                else {
                head=oldNode.next;
                    head.previous=null;
                    if (this.size()==2){
                        tail=head;
                    }
                }
            }
            else {
                
                Node<T> previous=oldNode.previous;
                if (oldNode==tail){
                    this.tail=previous;
                }
                Node<T> after=oldNode.next;
                previous.next=after;
                after.previous=previous;
                
            }
            /*
            if (place==this.size()-1){
                
                tail=oldNode.previous;
            }
             */
            
            
            
            return oldNode.value;
        }
        catch (Exception e){
            System.out.println("Position is out of bounds, cannot remove");
            return null;
            
        }
    }
    
    
    
    
    
    private Node<T> getNode(int position){
        Node<T> myNode=head;
        //iterating throught the list using the .next until you get the Node T at the given position
        for (int i=0;i<position;i++){
            myNode=myNode.next;
        }
        
        
        return myNode;
        
        //dont need try/catch because it is private
        
        
        
        
        
    }
    
    
    
    
    
}

class Node<T> {
    //setting up Node T class with two properties: value and next
    Node<T> next;
    T value;
    Node<T> previous;
    
    Node(T value){
        
        this.value=value;
        next=null;
        previous=null;
        
    }
    //nodes have no abilities
    
    
    
}