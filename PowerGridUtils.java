public class PowerGridUtils {
//public static Queue<Integer> po=new LinkedQueue<Integer>();

	// Return the IDs of all elements in the power grid pg in pre-order.
	public static Queue<Integer> collectPreorder(GT<PGElem> pg){
Queue<Integer> po=new LinkedQueue<Integer>();
if(pg==null) return po;
if(pg.empty()) return po;
pg.findRoot();
collectPreorder2(pg,po);
return po;
}
private static void collectPreorder2(GT<PGElem> pg,Queue<Integer>p){

int y =pg.retrieve().getId();
p.enqueue(y);
int size=pg.nbChildren();
if(size==0) return;
for(int i=0;i<size;i++){
pg.findChild(i);
collectPreorder2(pg,p);
pg.findParent();
}
}



	// Searches the power grid pg for the element with ID id. If found, it is made current and true is returned, otherwise false is returned.
	public static boolean find(GT<PGElem> pg, int id){
   boolean found=false;
   if(pg==null) return found;
  if(pg.empty()) return found;
 int preserve = pg.retrieve().getId();
 pg.findRoot();
  Queue<Integer>ids=collectPreorder(pg);
 int size=ids.length();
 int exist=0;
  for(int i=0;i<size;i++){
  exist=ids.serve();
 if(exist==id){
 find2(pg, id);
 found= true;
 return found;
 }
 }
 find2(pg,preserve);
 found= false;
 return found;
   }

  private static boolean find2(GT<PGElem> pg , int id){
if(pg.retrieve().getId()==id) return true;
   int s=pg.nbChildren();
 for(int i=0;i<s;i++)
 {
 pg.findChild(i);
 if(find2(pg,id)) return true;
 pg.findParent();
 }
 return false;
   }
	// Add the generator element gen to the power grid pg. This can only be done if the grid is empty. If successful, the method returns true. If there is already a generator, or gen is not of type Generator, false is returned.
public static boolean addGenerator(GT<PGElem> pg, PGElem gen){
try{
boolean added=false;
 if(pg==null) return added;
   if(!pg.empty()) return added;
   if(gen.getType()!=ElemType.Generator) return added;
   pg.insert(gen); added= true;
   return added;}
   catch(Exception e){ return false;}
  
   }
   
	// Attaches pgn to the element id and returns true if successful. Note that a consumer can only be attached to a transmitter, and no element can be be attached to it. The tree must not contain more than one generator located at the root. If id does not exist, or there is already aelement with the same ID as pgn, pgn is not attached, and the method retrurns false.
	public static boolean attach(GT<PGElem> pg, PGElem pgn, int id){
   try{
   boolean done=false;
   if(pg==null)return done;
   if(pgn.getType()==ElemType.Generator)
  addGenerator(pg,pgn);
if(!find(pg, id))
 return done; 
 ElemType elem=pg.retrieve().getType();
if(elem==ElemType.Consumer)
 return done;
if(elem==ElemType.Transmitter)
 if(pgn.getType()==ElemType.Consumer||pgn.getType()==ElemType.Transmitter)
{
  if(find(pg,pgn.getId())){ done= false;return done;}
 pg.insert(pgn);
 done= true;return done; }
if(elem==ElemType.Generator&&pgn.getType()==ElemType.Transmitter)
 {  if(find(pg,pgn.getId())) { done= false;return done;}
 pg.insert(pgn);
 done= true;return done; }

 done= false;return done;}catch(Exception e){ return false;}}

	// Removes element by ID, all corresponding subtree is removed. If removed, true is returned, false is returned otherwise.
	public static boolean remove(GT<PGElem> pg, int id){
   try{
   boolean removed=false;
   if(pg==null)return removed;
   if(pg.empty()) return removed;
   if(find(pg, id)){
         pg.remove();
         removed=true;
         return removed ;}
        removed= false;
        return removed;}catch(Exception e){ return false;}
 
   }


	// Computes total power that consumed by a element (and all its subtree). If id is incorrect, -1 is returned.
	public static double totalPower(GT<PGElem> pg, int id){
      if(pg==null)return -1;
   if(pg.empty()) return -1;
if(find(pg, id)){
      return totalPower2(pg,id);
      }
      else 
      return -1;}
 private static double totalPower2(GT<PGElem> pg, int id){
  int size=pg.nbChildren();
 if(size==0){//leaf
 if(pg.retrieve().getType()==ElemType.Consumer){
 return pg.retrieve().getPower();}
 else return 0;
 } else{
 double total=0;
 for(int i=0;i<size;i++)
 {
 pg.findChild(i);
 total+=totalPower2(pg, id);
 pg.findParent();
 }
 return total;
 }
 }

	// Checks if the power grid contains an overload. The method returns the ID of the first element preorder that has an overload and -1 if there is no overload.
   	public static int findOverload(GT<PGElem> pg){
      if(pg==null)return -1;
   if(pg.empty())return -1;
   
   pg.findRoot();
   return findOverload2(pg);
}
   
   private static int findOverload2(GT<PGElem> pg){ 
    int size=pg.nbChildren();
    if(pg.retrieve().getType()!=ElemType.Consumer )
    if(size==0)
    return -1;
    else{
     for(int i=0;i<size;i++){
     pg.findChild(i);
 find(pg, pg.retrieve().getId());
if(pg.retrieve().getType()==ElemType.Consumer)
    continue;
     PGElem p=pg.retrieve();
    if(p.getPower()<totalPower(pg, p.getId()))
       return p.getId();}}
       return -1;
    
    
    
    
}}