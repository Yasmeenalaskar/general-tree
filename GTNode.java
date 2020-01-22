class GTNode<T>
{
public T data;
public LinkedList<GTNode<T>> childList;


public GTNode(T e)
{
 data=e;
 childList=new LinkedList<GTNode<T>>();
}
}



