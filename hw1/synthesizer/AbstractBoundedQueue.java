package synthesizer;

/**
 * @author zwen
 * @Description
 * @create 2020-12-23 22:27
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    @Override
    public int capacity() {
        return capacity;
    }
    @Override
    public int fillCount() {
        return fillCount;
    }




}
