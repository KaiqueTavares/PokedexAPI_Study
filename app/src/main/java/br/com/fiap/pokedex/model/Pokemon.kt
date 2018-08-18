package br.com.fiap.pokedex.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Pokemon(
        //SERIALIZEDNAME RESPONSAVEL POR CONVERTER A VARIAVEL QUE DECLAREI PARA O NOME QUE EU COLOCAR NO VALUE
        @SerializedName ("number")val numero: String,
        @SerializedName ("name")val nome: String,
        @SerializedName ("imageURL") val urlImagem: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(numero)
        parcel.writeString(nome)
        parcel.writeString(urlImagem)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }
}