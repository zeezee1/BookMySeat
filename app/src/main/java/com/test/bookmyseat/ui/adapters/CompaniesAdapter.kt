package com.test.bookmyseat.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.bookmyseat.R
import com.test.bookmyseat.models.ProductionCompany

class CompaniesAdapter(var companies: ArrayList<ProductionCompany>) : RecyclerView.Adapter<CompaniesAdapter.CompanyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_company, parent, false)
        return CompanyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindView(companies[position])
    }

    override fun getItemCount(): Int {
        return companies.size
    }

    class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCompany = itemView.findViewById<ImageView>(R.id.ivCompany)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

        fun bindView(company: ProductionCompany) {
            Glide.with(ivCompany.context).load("https://image.tmdb.org/t/p/original" + company.logoPath).placeholder(R.drawable.error_placeholder).into(ivCompany)
            tvTitle.text = company.name
        }
    }
}