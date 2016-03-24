package vlad.com.br.tipcalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private double currentBillTotal;
    private int currentCustomPercent;
    private EditText tip10EditText, tip15EditText, tip20EditText, total10EditText, total15EditText, total20EditText;
    private TextView customTipTextView;
    private EditText billEditText, tipCustomEditText, totalCustomEditText;
    private SeekBar customSeekBar;
    private SeekBar.OnSeekBarChangeListener customSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    currentCustomPercent = progress;
                    updateCustom();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
    private TextWatcher billTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                currentBillTotal = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {
                currentBillTotal = 0.0;
            }
            updateCustom();
            updateStandart();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tipcalc);
        initialize();
    }

    private void initialize() {
        tip10EditText = (EditText) findViewById(R.id.tip10EditTextField);
        tip15EditText = (EditText) findViewById(R.id.tip15EditTextField);
        tip20EditText = (EditText) findViewById(R.id.tip20EditTextField);
        total10EditText = (EditText) findViewById(R.id.total10EditTextField);
        total15EditText = (EditText) findViewById(R.id.total15EditTextField);
        total20EditText = (EditText) findViewById(R.id.total20EditTextField);
        customTipTextView = (TextView) findViewById(R.id.tipCustomTextView);
        tipCustomEditText = (EditText) findViewById(R.id.tipCustomEditTextField);
        totalCustomEditText = (EditText) findViewById(R.id.totalCustomEditTextField);
        billEditText = (EditText) findViewById(R.id.billEditText);
        customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);
        currentCustomPercent = customSeekBar.getProgress();
        billEditText.addTextChangedListener(billTextWatcher);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }

    private void updateStandart() {
        double tenPercentTip = currentBillTotal * .10;
        double tenPercentTotal = currentBillTotal + tenPercentTip;
        double fifteenPercentTip = currentBillTotal * .15;
        double fifteenPercentTotal = currentBillTotal + fifteenPercentTip;
        double twentyfPercentTip = currentBillTotal * .20;
        double twentyPercentTotal = currentBillTotal + twentyfPercentTip;

        tip10EditText.setText(String.format("%.02f", tenPercentTip));
        total10EditText.setText(String.format("%.02f", tenPercentTotal));
        tip15EditText.setText(String.format("%.02f", fifteenPercentTip));
        total15EditText.setText(String.format("%.02f", fifteenPercentTotal));
        tip20EditText.setText(String.format("%.02f", twentyfPercentTip));
        total20EditText.setText(String.format("%.02f", twentyPercentTotal));
    }

    private void updateCustom() {
        System.out.println(currentCustomPercent);
        customTipTextView.setText(currentCustomPercent + "%");

        double customTipAmount = currentBillTotal * currentCustomPercent * .01;
        double customTotalAmount = currentBillTotal + customTipAmount;

        tipCustomEditText.setText(String.format("%.02f", customTipAmount));
        totalCustomEditText.setText(String.format("%.02f", customTotalAmount));
    }
}
