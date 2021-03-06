package io.kimo.base.v7;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import io.kimo.base.Base;
import io.kimo.base.Presenter;

public abstract class BaseActionBarActivity <P extends Presenter>
        extends ActionBarActivity
        implements Base.View<P> {

    private P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResource());
        mPresenter = injectDependencies();

        if(getPresenter() == null) {
            throw new IllegalArgumentException("You must inject the " +
                    "dependencies before retrieving the presenter");
        } else {
            mapUI(findViewById(android.R.id.content));
            configureUI();
        }

        mPresenter.createView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroyView();
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }
}
