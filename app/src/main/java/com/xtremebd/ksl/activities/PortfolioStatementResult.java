package com.xtremebd.ksl.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.IpoInformation;
import com.xtremebd.ksl.models.Resp;
import com.xtremebd.ksl.models.RetDataTable;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PortfolioStatementResult extends AppCompatActivity {

    @BindView(R.id.tvClientId)
    TextView tvClientId;

    @BindView(R.id.tvClientName)
    TextView tvClientName;

    @BindView(R.id.tvBoId)
    TextView tvBoID;

    @BindView(R.id.tvLedgerbalance)
    TextView tvLedgerBalance;

    @BindView(R.id.tvMarginType)
    TextView tvMarginType;

    @BindView(R.id.tvTotalDeposit)
    TextView tvTotalDeposit;

    @BindView(R.id.tvTotalWithdrawal)
    TextView tvTotalWithdrawal;

    @BindView(R.id.tvNetRefundAmount)
    TextView tvNetRefundAmount;

    @BindView(R.id.tvAllotmentAmount)
    TextView tvAllotmentAmount;

    @BindView(R.id.tvAllotmentQuantity)
    TextView tvAllotmentQuantity;

    @BindView(R.id.tvApplicationAmount)
    TextView tvApplicationAmount;

    @BindView(R.id.tvStockValue)
    TextView tvStockValue;

    @BindView(R.id.tvStockCurrentBalance)
    TextView tvStockCurrentBalance;

    @BindView(R.id.tvMarketValue)
    TextView tvMarketValue;

    @BindView(R.id.tvMarketValueCurrentBalance)
    TextView tvMarketValueCurrentBalance;

    @BindView(R.id.tvOwnersEquity)
    TextView tvOwnersEquity;

    @BindView(R.id.tvShareReceivedIn)
    TextView tvShareReceivedIn;

    @BindView(R.id.tvRealisedGainLoss)
    TextView tvRealisedGainLoss;

    @BindView(R.id.tvNetRealizedGainLoss)
    TextView tvNetRealisedGainLoss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_statement_result);
        ButterKnife.bind(this);
        TopBar.attach(this, "PORTFOLIO STATEMENT");

        Logger.addLogAdapter(new AndroidLogAdapter());
        String resp = getIntent().getStringExtra("response");

        Resp response = Geson.g().fromJson(resp, Resp.class);
        RetDataTable mainData = response.getRetDataTable().get(0);
        IpoInformation ipoInformation = mainData.getIpoInformation().get(0);


        tvClientId.setText(mainData.getClientId());
        tvClientName.setText(mainData.getFahFullName());
        tvMarginType.setText(mainData.getAccountTypeConst());
        tvLedgerBalance.setText(mainData.getLedgerBalance() + "");
        tvTotalDeposit.setText(mainData.getTotalDeposit());
        tvTotalWithdrawal.setText(mainData.getTotalWithdrawal());
        tvNetRefundAmount.setText(ipoInformation.getNetRefundAmount());
        tvAllotmentAmount.setText(ipoInformation.getAllotmentAmount());
        tvAllotmentQuantity.setText(ipoInformation.getAllotmentQuantity());

        tvApplicationAmount.setText(ipoInformation.getApplicationAmount());

        tvStockValue.setText(mainData.getStockValue());

        //tvStockCurrentBalance.setText(mainData.getbal);
        tvMarketValue.setText(mainData.getMarketValue());
        //tvMarketValueCurrentBalance.setText();

        tvOwnersEquity.setText(mainData.getOwnersEquity() + "");
        tvShareReceivedIn.setText(mainData.getTransferIn());
        tvRealisedGainLoss.setText(mainData.getRealizedGainLoss() + "");
        tvNetRealisedGainLoss.setText(mainData.getNetRealizedGainLoss() + "");


    }
}
