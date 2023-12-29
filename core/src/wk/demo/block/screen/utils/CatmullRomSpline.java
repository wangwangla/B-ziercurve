package wk.demo.block.screen.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import wk.demo.block.utils.Vector3;

public class CatmullRomSpline implements Serializable {
    private static final long serialVersionUID = -3290464799289771451L;
    private List<Vector3> controlPoints = new ArrayList();
    Vector3 T1 = new Vector3();
    Vector3 T2 = new Vector3();

    public CatmullRomSpline() {
    }

    public void add(Vector3 point) {
        this.controlPoints.add(point);
    }

    public List<Vector3> getControlPoints() {
        return this.controlPoints;
    }

    public List<Vector3> getPath(int numPoints) {
        ArrayList<Vector3> points = new ArrayList();
        if (this.controlPoints.size() < 4) {
            return points;
        } else {
            Vector3 T1 = new Vector3();
            Vector3 T2 = new Vector3();

            for(int i = 1; i <= this.controlPoints.size() - 3; ++i) {
                points.add(this.controlPoints.get(i));
                float increment = 1.0F / (float)(numPoints + 1);
                float t = increment;
                T1.set((Vector3)this.controlPoints.get(i + 1)).sub((Vector3)this.controlPoints.get(i - 1)).mul(0.5F);
                T2.set((Vector3)this.controlPoints.get(i + 2)).sub((Vector3)this.controlPoints.get(i)).mul(0.5F);

                for(int j = 0; j < numPoints; ++j) {
                    float h1 = 2.0F * t * t * t - 3.0F * t * t + 1.0F;
                    float h2 = -2.0F * t * t * t + 3.0F * t * t;
                    float h3 = t * t * t - 2.0F * t * t + t;
                    float h4 = t * t * t - t * t;
                    Vector3 point = (new Vector3((Vector3)this.controlPoints.get(i))).mul(h1);
                    point.add(((Vector3)this.controlPoints.get(i + 1)).tmp().mul(h2));
                    point.add(T1.tmp().mul(h3));
                    point.add(T2.tmp().mul(h4));
                    points.add(point);
                    t += increment;
                }
            }

            if (this.controlPoints.size() >= 4) {
                points.add(this.controlPoints.get(this.controlPoints.size() - 2));
            }

            return points;
        }
    }

    public void getPath(Vector3[] points, int numPoints) {
        int idx = 0;
        if (this.controlPoints.size() >= 4) {
            for(int i = 1; i <= this.controlPoints.size() - 3; ++i) {
                points[idx++].set((Vector3)this.controlPoints.get(i));
                float increment = 1.0F / (float)(numPoints + 1);
                float t = increment;
                this.T1.set((Vector3)this.controlPoints.get(i + 1)).sub((Vector3)this.controlPoints.get(i - 1)).mul(0.5F);
                this.T2.set((Vector3)this.controlPoints.get(i + 2)).sub((Vector3)this.controlPoints.get(i)).mul(0.5F);

                for(int j = 0; j < numPoints; ++j) {
                    float h1 = 2.0F * t * t * t - 3.0F * t * t + 1.0F;
                    float h2 = -2.0F * t * t * t + 3.0F * t * t;
                    float h3 = t * t * t - 2.0F * t * t + t;
                    float h4 = t * t * t - t * t;
                    Vector3 point = points[idx++].set((Vector3)this.controlPoints.get(i)).mul(h1);
                    point.add(((Vector3)this.controlPoints.get(i + 1)).tmp().mul(h2));
                    point.add(this.T1.tmp().mul(h3));
                    point.add(this.T2.tmp().mul(h4));
                    t += increment;
                }
            }

            points[idx].set((Vector3)this.controlPoints.get(this.controlPoints.size() - 2));
        }
    }

    public List<Vector3> getTangents(int numPoints) {
        ArrayList<Vector3> tangents = new ArrayList();
        if (this.controlPoints.size() < 4) {
            return tangents;
        } else {
            Vector3 T1 = new Vector3();
            Vector3 T2 = new Vector3();

            for(int i = 1; i <= this.controlPoints.size() - 3; ++i) {
                float increment = 1.0F / (float)(numPoints + 1);
                float t = increment;
                T1.set((Vector3)this.controlPoints.get(i + 1)).sub((Vector3)this.controlPoints.get(i - 1)).mul(0.5F);
                T2.set((Vector3)this.controlPoints.get(i + 2)).sub((Vector3)this.controlPoints.get(i)).mul(0.5F);
                tangents.add((new Vector3(T1)).nor());

                for(int j = 0; j < numPoints; ++j) {
                    float h1 = 6.0F * t * t - 6.0F * t;
                    float h2 = -6.0F * t * t + 6.0F * t;
                    float h3 = 3.0F * t * t - 4.0F * t + 1.0F;
                    float h4 = 3.0F * t * t - 2.0F * t;
                    Vector3 point = (new Vector3((Vector3)this.controlPoints.get(i))).mul(h1);
                    point.add(((Vector3)this.controlPoints.get(i + 1)).tmp().mul(h2));
                    point.add(T1.tmp().mul(h3));
                    point.add(T2.tmp().mul(h4));
                    tangents.add(point.nor());
                    t += increment;
                }
            }

            if (this.controlPoints.size() >= 4) {
                tangents.add(T1.set((Vector3)this.controlPoints.get(this.controlPoints.size() - 1)).sub((Vector3)this.controlPoints.get(this.controlPoints.size() - 3)).mul(0.5F).cpy().nor());
            }

            return tangents;
        }
    }

    public List<Vector3> getTangentNormals2D(int numPoints) {
        ArrayList<Vector3> tangents = new ArrayList();
        if (this.controlPoints.size() < 4) {
            return tangents;
        } else {
            Vector3 T1 = new Vector3();
            Vector3 T2 = new Vector3();

            for(int i = 1; i <= this.controlPoints.size() - 3; ++i) {
                float increment = 1.0F / (float)(numPoints + 1);
                float t = increment;
                T1.set((Vector3)this.controlPoints.get(i + 1)).sub((Vector3)this.controlPoints.get(i - 1)).mul(0.5F);
                T2.set((Vector3)this.controlPoints.get(i + 2)).sub((Vector3)this.controlPoints.get(i)).mul(0.5F);
                Vector3 normal = (new Vector3(T1)).nor();
                float x = normal.x;
                normal.x = normal.y;
                normal.y = -x;
                tangents.add(normal);

                for(int j = 0; j < numPoints; ++j) {
                    float h1 = 6.0F * t * t - 6.0F * t;
                    float h2 = -6.0F * t * t + 6.0F * t;
                    float h3 = 3.0F * t * t - 4.0F * t + 1.0F;
                    float h4 = 3.0F * t * t - 2.0F * t;
                    Vector3 point = (new Vector3((Vector3)this.controlPoints.get(i))).mul(h1);
                    point.add(((Vector3)this.controlPoints.get(i + 1)).tmp().mul(h2));
                    point.add(T1.tmp().mul(h3));
                    point.add(T2.tmp().mul(h4));
                    point.nor();
                    x = point.x;
                    point.x = point.y;
                    point.y = -x;
                    tangents.add(point);
                    t += increment;
                }
            }

            return tangents;
        }
    }

    public List<Vector3> getTangentNormals(int numPoints, Vector3 up) {
        List<Vector3> tangents = this.getTangents(numPoints);
        ArrayList<Vector3> normals = new ArrayList();
        Iterator i$ = tangents.iterator();

        while(i$.hasNext()) {
            Vector3 tangent = (Vector3)i$.next();
            normals.add((new Vector3(tangent)).crs(up).nor());
        }

        return normals;
    }

    public List<Vector3> getTangentNormals(int numPoints, List<Vector3> up) {
        List<Vector3> tangents = this.getTangents(numPoints);
        ArrayList<Vector3> normals = new ArrayList();
        int i = 0;
        Iterator i$ = tangents.iterator();

        while(i$.hasNext()) {
            Vector3 tangent = (Vector3)i$.next();
            normals.add((new Vector3(tangent)).crs((Vector3)up.get(i++)).nor());
        }

        return normals;
    }
}
