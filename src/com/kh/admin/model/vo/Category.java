package com.kh.admin.model.vo;

public class Category {
   private int categoryNo;//CATEGORY_NO   NUMBER
   private String categoryName;//CATEGORY_NAME   VARCHAR2(30 BYTE)
   public Category() {
      super();
   }
   public Category(int categoryNo, String categoryName) {
      super();
      this.categoryNo = categoryNo;
      this.categoryName = categoryName;
   }
   public int getCategoryNo() {
      return categoryNo;
   }
   public void setCategoryNo(int categoryNo) {
      this.categoryNo = categoryNo;
   }
   public String getCategoryName() {
      return categoryName;
   }
   public void setCategoryName(String categoryName) {
      this.categoryName = categoryName;
   }
   @Override
   public String toString() {
      return "Category [categoryNo=" + categoryNo + ", categoryName=" + categoryName + "]";
   }
   
}