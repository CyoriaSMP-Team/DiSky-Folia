import java.nio.file.Files;
import java.nio.file.Path;
import org.objectweb.asm.*;
public class RewriteLegacyScheduler {
 public static void main(String[] args) throws Exception {
   final int[] patches={0};
   var cr=new ClassReader(Files.readAllBytes(Path.of(args[0])));
   var cw=new ClassWriter(cr,ClassWriter.COMPUTE_MAXS);
   cr.accept(new ClassVisitor(Opcodes.ASM9,cw) {
     @Override public MethodVisitor visitMethod(int access,String name,String desc,String signature,String[] exceptions) {
       MethodVisitor method=super.visitMethod(access,name,desc,signature,exceptions);
       return new MethodVisitor(Opcodes.ASM9,method) {
         @Override public void visitMethodInsn(int opcode,String owner,String methodName,String descriptor,boolean itf) {
           if(opcode==Opcodes.INVOKEINTERFACE && owner.equals("org/bukkit/scheduler/BukkitScheduler") &&
              (methodName.equals("runTask") || methodName.equals("runTaskAsynchronously")) &&
              descriptor.equals("(Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;)Lorg/bukkit/scheduler/BukkitTask;")) {
             patches[0]++;
             super.visitMethodInsn(Opcodes.INVOKESTATIC,"net/itsthesky/disky/DiSkyLegacyScheduler",methodName,
                 "(Lorg/bukkit/scheduler/BukkitScheduler;Lorg/bukkit/plugin/Plugin;Ljava/lang/Runnable;)Lorg/bukkit/scheduler/BukkitTask;",false);
           } else super.visitMethodInsn(opcode,owner,methodName,descriptor,itf);
         }
       };
     }
   },0);
   if(patches[0]<1) throw new IllegalStateException("No scheduler patch applied to "+args[0]);
   Files.createDirectories(Path.of(args[1]).getParent());Files.write(Path.of(args[1]),cw.toByteArray());
   System.out.println("PATCHED "+args[0]+" scheduler calls="+patches[0]);
 }
}
