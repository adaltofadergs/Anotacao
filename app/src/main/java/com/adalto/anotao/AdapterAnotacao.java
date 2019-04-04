package com.adalto.anotao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterAnotacao extends BaseAdapter {

    private Context context;
    private List<Anotacao> lista;
    private LayoutInflater inflater;

    public AdapterAnotacao(Context context, List<Anotacao> lista){
        this.context = context;
        this.lista = lista;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Suporte item;

        if( convertView == null ){
            convertView = inflater.inflate
                    (R.layout.layout_lista_notas, null);
            item = new Suporte();
            item.tvCodigo = (TextView)
                    convertView.findViewById(R.id.tvListaCodigo);
            item.tvTitulo = (TextView)
                    convertView.findViewById(R.id.tvListaTitulo);
            item.tvTexto = (TextView)
                    convertView.findViewById(R.id.tvListaTexto);

            convertView.setTag(item);
        }else {
            item = (Suporte) convertView.getTag();
        }

        Anotacao nota = lista.get( position );
        item.tvCodigo.setText( String.valueOf( nota.getId() ) );
        item.tvTitulo.setText(  nota.getTitulo()  );
        item.tvTexto.setText(  nota.getTexto()  );



        return convertView;
    }

    private class Suporte{
        TextView tvCodigo, tvTitulo, tvTexto;
    }

}










