public class LinkedGT<T> implements GT<T>{
 GTNode<T> root;
 GTNode<T> current;
 
 public LinkedGT(){
 root =current=null;
 }
 
	// Return true if the tree is empty
public boolean empty(){
return root==null;
}
 
 	// Return true if the tree is full
public boolean full(){
return false;
}

	// Return the data of the current node
public T retrieve(){
try{
  return current.data;}catch(Exception e){ return null;}
   
   }
   
   // Update the data of the current node
public void update(T e){
try{
current.data=e;}catch(Exception ex){ return ;}
}

 
	// If the tree is empty e is inserted as root. If the tree is not empty, e is added as a child of the current node. The new node is made current and true is returned.
public boolean insert(T e){
try{
//case 1:
if(empty()){
root=current=new GTNode<T>(e);
return true;
}
//case 2:
else{
GTNode<T>child=new GTNode<T>(e);
current.childList.insert(child);
current=child;
return true; 
}}catch(Exception ee){ return false;}}



	// Return the number of children of the current node.
public int nbChildren(){
try{
if(current.childList.empty()) 
return 0;

int count=1;
current.childList.findFirst();
while(!current.childList.last()){
count++;
current.childList.findNext();}
return count;
//return 0;
}catch(Exception e){ return 0;}}

// Put current on the i-th child of the current node (starting from 0), if it exists, and return true. If the child does not exist, current is not changed and the method returns false.
public boolean findChild(int i){
try{
boolean found=false;
if(current == null)
      return found;
      LinkedList<GTNode<T>> y=current.childList;
      if(y.empty()) return found;
y.findFirst();
int yasmeen=0;
GTNode<T> child;
while(!y.last()){
child=y.retrieve();
if(yasmeen==i)
{ current=child;
found=true;
return found;}
y.findNext();
yasmeen++;
}
child=y.retrieve();
if(yasmeen==i){
current=child;
found=true;
return found;}
found=false;
return found;
}catch(Exception e){ return false;}}

// Put current on the parent of the current node. If the parent does not exist, current does not change and false is returned.
public boolean findParent(){
if(current==root)//empty? on root
return false;
findParent(root);
return true;
}
private void findParent(GTNode<T> parent){
LinkedList<GTNode<T>> y=parent.childList;
y.findFirst();
if(y.empty()) return;
boolean found=false;
GTNode<T> child;
while(!y.last()){
child=y.retrieve();
if(child==current){
current=parent;
found=true;}
findParent(child);
if(found) return;
y.findNext();
}
child=y.retrieve();
if(child==current){
current=parent;
found=true;}
findParent(child);
if(found) return;
return;
}

// Put current on the root. If the tree is empty nothing happens.
public void findRoot(){
//if(!empty())
try{
current=root;
}catch(Exception e){ return ;}}
// Remove the current subtree. The parent of current, if it exists, becomes the new current.
public void remove(){
try{
GTNode<T>yasmeen =current;
if(yasmeen==root){
current=root=null;
return;}
 if(!findParent()){current=null;}
 else {
LinkedList<GTNode<T>> y=current.childList;
y.findFirst();
  GTNode<T> child;
 while(!y.last()){
child=y.retrieve();
if(child==yasmeen){
y.remove();
return;
 }
 else 
 y.findNext();
 }
 child=y.retrieve();
if(child==yasmeen){
y.remove();
return;
 
 }}
 return;

}catch(Exception e){ return ;}
}}