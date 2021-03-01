package com.example.bilet_ex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChartFragment extends Fragment {
    List<Car> cars = new ArrayList<>();
    LineChart lineChart = new LineChart(getContext().getApplicationContext(), (ArrayList<Car>)cars);
    public ChartFragment() {
        // Required empty public constructor
    }

    public static ChartFragment newInstance(ArrayList<Car> cars) {
        ChartFragment fragment = new ChartFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("cars", cars);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(lineChart, container, false);
//        return view;
        return lineChart;
    }


}
class LineChart extends View {
    ArrayList<Car> cars;
    ArrayList<Integer> masiniMotorina;
    ArrayList<Integer> masiniBenzina;

    public LineChart(Context context, ArrayList<Car> cars) {
        super(context);
        this.cars = cars;
        masiniMotorina = new ArrayList<>();
        masiniBenzina = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Car car : cars) {
            if (car.getCarburant().equals("motorina")) {
                masiniMotorina.add(car.getNrZile());
            } else {
                masiniBenzina.add(car.getNrZile());
            }
        }
        Paint pensula = new Paint();

        int nB = masiniBenzina.size();
        int nM = masiniMotorina.size();
        int widthSegmentB = canvas.getWidth()/nB;
        int widthSegmentM = canvas.getWidth()/nM;
        int maxValueB = extractMaxValue(masiniBenzina);
        int maxValueM = extractMaxValue(masiniMotorina);
        pensula.setColor(Color.RED);
        for (int i = 0; i< nB; i++) {
            int x = i*widthSegmentB;
            int y = canvas.getHeight() - masiniBenzina.get(i) * canvas.getHeight() / maxValueB;
            if (i == 0) {
                canvas.drawLine(0,0,x,y, pensula);
            }
            else {
                int xPrecedent = (i-1)*widthSegmentB;
                int yPrecedent = canvas.getHeight() - masiniBenzina.get(i-1) * canvas.getHeight() / maxValueB;
                canvas.drawLine(xPrecedent, yPrecedent,x, y, pensula);
            }
        }
    }

    private int extractMaxValue(ArrayList<Integer> list) {
        int maxValue = 0;
        for (Integer value : list) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }
}