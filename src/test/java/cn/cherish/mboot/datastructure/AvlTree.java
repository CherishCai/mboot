package cn.cherish.mboot.datastructure;

import java.util.Comparator;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/5/4 10:57
 */
public class AvlTree<AnyType extends Comparable<? super AnyType>> {
    private AvlNode<AnyType> root;
    private Comparator<? super AnyType> cmp;

    /*********  AVL树节点数据结构定义   **********/
    private static class AvlNode<AnyType> {
        AnyType element;
        AvlNode<AnyType> left;
        AvlNode<AnyType> right;
        int height;

        AvlNode(AnyType theElement) {
            this(theElement, null, null);
        }

        AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
        }
    }

    public AvlTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(AnyType element) {
        root = insert(element, root);
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    public void remove(AnyType element) {
        root = remove(element, root);
    }

    private int myCompare(AnyType lhs, AnyType rhs) {
        if (cmp != null)
            return cmp.compare(lhs, rhs);
        else
            return lhs.compareTo(rhs);
    }

    private boolean contains(AnyType x, AvlNode<AnyType> t) {
        //空树处理
        if (t == null)
            return false;
        //正常情况处理
        //@方式一：对Comparable型的对象进行比较
        //int compareResult = x.compareTo(t.element);
        //@方式二：使用一个函数对象而不是要求这些项是Comparable的
        int compareResult = myCompare(x, t.element);
        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;
    }

    private int height(AvlNode<AnyType> t) {
        return t == null ? -1 : t.height;
    }

    private AvlNode<AnyType> findMin(AvlNode<AnyType> t) {
        if (t == null)
            return null;
        if (t.left == null)
            return t;
        return findMin(t.left);
    }

    private AvlNode<AnyType> findMax(AvlNode<AnyType> t) {
        if (t == null)
            return null;
        if (t.right == null)
            return t;
        return findMax(t.right);
    }

    private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t) {
        if (t == null)
            return new AvlNode<>(x, null, null);
        int compareResult = myCompare(x, t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (myCompare(x, t.left.element) < 0)        //左左情况
                    t = rotateWithLeftChild(t);
                else                                        //左右情况
                    t = doubleWithLeftChild(t);
            }
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (myCompare(x, t.right.element) < 0)        //右左情况
                    t = doubleWithRightChild(t);
                else                                        //右右情况
                    t = rotateWithRightChild(t);
            }
        }
        //完了之后更新height值
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private AvlNode<AnyType> remove(AnyType x, AvlNode<AnyType> t) {
        if (t == null)
            return null;
        int compareResult = myCompare(x, t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
            //完了之后验证该子树是否平衡
            if (t.right != null) {        //若右子树为空，则一定是平衡的，此时左子树相当对父节点深度最多为1, 所以只考虑右子树非空情况
                if (t.left == null) {        //若左子树删除后为空，则需要判断右子树
                    if (height(t.right) - t.height == 2) {
                        AvlNode<AnyType> k = t.right;
                        if (k.right != null) {        //右子树存在，按正常情况单旋转
                            System.out.println("-11111");
                            t = rotateWithRightChild(t);
                        } else {                        //否则是右左情况，双旋转
                            System.out.println("-22222");
                            t = doubleWithRightChild(t);
                        }
                    }
                } else {                    //否则判断左右子树的高度差
                    //左子树自身也可能不平衡，故先平衡左子树，再考虑整体
                    AvlNode<AnyType> k = t.left;
                    //删除操作默认用右子树上最小节点补删除的节点
                    //k的左子树高度不低于k的右子树
                    if (k.right != null) {
                        if (height(k.left) - height(k.right) == 2) {
                            AvlNode<AnyType> m = k.left;
                            if (m.left != null) {        //左子树存在，按正常情况单旋转
                                System.out.println("-33333");
                                k = rotateWithLeftChild(k);
                            } else {                        //否则是左右情况，双旋转
                                System.out.println("-44444");
                                k = doubleWithLeftChild(k);
                            }
                        }
                    } else {
                        if (height(k.left) - k.height == 2) {
                            AvlNode<AnyType> m = k.left;
                            if (m.left != null) {        //左子树存在，按正常情况单旋转
                                System.out.println("-hhhhh");
                                k = rotateWithLeftChild(k);
                            } else {                        //否则是左右情况，双旋转
                                System.out.println("-iiiii");
                                k = doubleWithLeftChild(k);
                            }
                        }
                    }
                    if (height(t.right) - height(t.left) == 2) {
                        //右子树自身一定是平衡的，左右失衡的话单旋转可以解决问题
                        System.out.println("-55555");
                        t = rotateWithRightChild(t);
                    }
                }
            }
            //完了之后更新height值
            t.height = Math.max(height(t.left), height(t.right)) + 1;
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
            //下面验证子树是否平衡
            if (t.left != null) {            //若左子树为空，则一定是平衡的，此时右子树相当对父节点深度最多为1
                if (t.right == null) {        //若右子树删除后为空，则只需判断左子树
                    if (height(t.left) - t.height == 2) {
                        AvlNode<AnyType> k = t.left;
                        if (k.left != null) {
                            System.out.println("-66666");
                            t = rotateWithLeftChild(t);
                        } else {
                            System.out.println("-77777");
                            t = doubleWithLeftChild(t);
                        }
                    }
                } else {                //若右子树删除后非空，则判断左右子树的高度差
                    //右子树自身也可能不平衡，故先平衡右子树，再考虑整体
                    AvlNode<AnyType> k = t.right;
                    //删除操作默认用右子树上最小节点（靠左）补删除的节点
                    //k的右子树高度不低于k的左子树
                    if (k.left != null) {
                        if (height(k.right) - height(k.left) == 2) {
                            AvlNode<AnyType> m = k.right;
                            if (m.right != null) {        //右子树存在，按正常情况单旋转
                                System.out.println("-88888");
                                k = rotateWithRightChild(k);
                            } else {                        //否则是右左情况，双旋转
                                System.out.println("-99999");
                                k = doubleWithRightChild(k);
                            }
                        }
                    } else {
                        if (height(k.right) - k.height == 2) {
                            AvlNode<AnyType> m = k.right;
                            if (m.right != null) {        //右子树存在，按正常情况单旋转
                                System.out.println("-aaaaa");
                                k = rotateWithRightChild(k);
                            } else {                        //否则是右左情况，双旋转
                                System.out.println("-bbbbb");
                                k = doubleWithRightChild(k);
                            }
                        }
                    }
                    if (height(t.left) - height(t.right) == 2) {
                        //左子树自身一定是平衡的，左右失衡的话单旋转可以解决问题
                        System.out.println("-ccccc");
                        t = rotateWithLeftChild(t);
                    }
                }
            }
            //完了之后更新height值
            t.height = Math.max(height(t.left), height(t.right)) + 1;
        } else if (t.left != null && t.right != null) {
            //默认用其右子树的最小数据代替该节点的数据并递归的删除那个节点
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
            if (t.right == null) {        //若右子树删除后为空，则只需判断左子树与根的高度差
                if (height(t.left) - t.height == 2) {
                    AvlNode<AnyType> k = t.left;
                    if (k.left != null) {
                        System.out.println("-ddddd");
                        t = rotateWithLeftChild(t);
                    } else {
                        System.out.println("-eeeee");
                        t = doubleWithLeftChild(t);
                    }
                }
            } else {                //若右子树删除后非空，则判断左右子树的高度差
                //右子树自身也可能不平衡，故先平衡右子树，再考虑整体
                AvlNode<AnyType> k = t.right;
                //删除操作默认用右子树上最小节点（靠左）补删除的节点

                if (k.left != null) {
                    if (height(k.right) - height(k.left) == 2) {
                        AvlNode<AnyType> m = k.right;
                        if (m.right != null) {        //右子树存在，按正常情况单旋转
                            System.out.println("-fffff");
                            k = rotateWithRightChild(k);
                        } else {                        //否则是右左情况，双旋转
                            System.out.println("-ggggg");
                            k = doubleWithRightChild(k);
                        }
                    }
                } else {
                    if (height(k.right) - k.height == 2) {
                        AvlNode<AnyType> m = k.right;
                        if (m.right != null) {        //右子树存在，按正常情况单旋转
                            System.out.println("-hhhhh");
                            k = rotateWithRightChild(k);
                        } else {                        //否则是右左情况，双旋转
                            System.out.println("-iiiii");
                            k = doubleWithRightChild(k);
                        }
                    }
                }
                //左子树自身一定是平衡的，左右失衡的话单旋转可以解决问题
                if (height(t.left) - height(t.right) == 2) {
                    System.out.println("-jjjjj");
                    t = rotateWithLeftChild(t);
                }
            }
            //完了之后更新height值
            t.height = Math.max(height(t.left), height(t.right)) + 1;
        } else {
            System.out.println("-kkkkk");
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    //左左情况单旋转
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;        //返回新的根
    }

    //右右情况单旋转
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k2) {
        AvlNode<AnyType> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.right), k2.height) + 1;
        return k1;        //返回新的根
    }

    //左右情况
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3) {
        try {
            k3.left = rotateWithRightChild(k3.left);
        } catch (NullPointerException e) {
            System.out.println("k.left.right为：" + k3.left.right);
            throw e;
        }
        return rotateWithLeftChild(k3);
    }

    //右左情况
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k3) {
        try {
            k3.right = rotateWithLeftChild(k3.right);
        } catch (NullPointerException e) {
            System.out.println("k.right.left为：" + k3.right.left);
            throw e;
        }
        return rotateWithRightChild(k3);
    }
}
