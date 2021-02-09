# 贝塞尔曲线



## 使用

Actions动画  求百分比

执行apply方法

```java
public float apply (float a) {
    if (a <= 0.5f) return (float)Math.pow(a * 2, power) / 2;
    return (float)Math.pow((a - 1) * 2, power) / (power % 2 == 0 ? -2 : 2) + 1;
}

```
直接计算结果，百分比来取数值，  

计算数值

```java
public void jisuan(Array<Vector2> controlPoint){
    int n = controlPoint.size - 1; //
    int i, r;
    float u;
    // u的步长决定了曲线点的精度
    for (u = 0; u <= 1; u += 0.01) {
        Vector2 p[] = new Vector2[n + 1];
        for (i = 0; i <= n; i++) {
            p[i] = new Vector2(controlPoint.get(i));
        }
        for (r = 1; r <= n; r++) {
            for (i = 0; i <= n - r; i++) {
                p[i].x = (1 - u) * p[i].x + u * p[i + 1].x;
                p[i].y = (1 - u) * p[i].y + u * p[i + 1].y;
            }
        }
        array1.add(p[0]);
    }
}
```

复写apply

```java
public float apply (float a) {
    var num = array1.get(array1.size * a);
    value = num / height ;
}
```

## 自定义动画

### 使用

```java
image1.addAction(Actions.scaleTo(3,3,3, new Bse(array1)));
```

### 复写方法

```java
public class Bse extends Interpolation{
    private Array<Vector2> array;
    public Bse (Array<Vector2> array) {
        this.array = array;
    }

    public float apply (float a) {
        //我们使用下标取值  所以转换为int
        int v = (int)(a * 100.0F);
        System.out.println(array.get(v).y);
        //工具的高为720，所以这里除以720
        return array.get(v).y/720;
    }
}
```


















