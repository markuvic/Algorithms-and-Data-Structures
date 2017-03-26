class BT {
  BT L; BT R;
  BT( BT l, BT r ) { L = l; R = r; }
}


public class MirrorTree {

   public BT tree, mirror; // A tree and its mirror

   private char[] a;       // 0/1 array for tree
   private int k;          // used by build()

   MirrorTree( String s ) {
      a = s.toCharArray();
      k = -1;
      tree = build();
      mirror = mirrorTree( clone( tree ) );
   }

   BT build() { return( a[++k] == '0' ? null : new BT( build(), build() )); }
  
   public String preord ( BT t ) {
	   if(t==null){
		   return "0";
	   }else{
		   return "1"+preord(t.L)+preord(t.R);
	   }
   } // FILL IN THE CODE.
   
   public String toString() {
	   return preord(tree)+"\n"+preord(mirror);
   } // FILL IN THE CODE.
    
   public BT clone( BT t ) {
	   BT cloneTree;
	   String tStr=preord(t);
	   a=tStr.toCharArray();
	   k =-1;
	   cloneTree = build();
	   return cloneTree;
	   
   } // FILL IN THE CODE.

   public BT mirrorTree( BT t ) {
	   
	   if(t==null){
		   return null;
	   }
	   
	   if(t.L!=null){
		   t.L=mirrorTree(t.L);
	   }
	   
	   if(t.R!=null){
		   
		   t.R=mirrorTree(t.R);
		   
		   t.R.R=clone(t);
		   t.R.R.R=null;
		    
		   t=t.R;
	   }
	   
	   return t;
   } // FILL IN THE CODE.

   // YOU CAN OMIT MAIN OR NOT. USE IT FOR TESTING.
   public static void main ( String[] args ) {
      MirrorTree mt = new MirrorTree( args[0] );
      System.out.println( mt+"\ntree and mirror" );
      System.out.println( new MirrorTree( mt.preord( mt.mirror ) ) ); // sanity check
   }
}
