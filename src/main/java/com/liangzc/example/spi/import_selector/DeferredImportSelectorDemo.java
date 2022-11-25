package com.liangzc.example.spi.import_selector;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class DeferredImportSelectorDemo implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        System.out.println("=====DeferredImportSelectorDemo.selectImports");
        return new String[]{DeferredBean.class.getName()};
    }

    @Override
    public Class<? extends Group> getImportGroup() {
        return DeferredImportSelectorGroupDemo.class;
    }


    /**
     * 负责收集实例化的类
     */
    private static class DeferredImportSelectorGroupDemo implements Group{
        List<Entry> list = new ArrayList<>();

        /**
         * 负责收集需要实例化的类
         * @param annotationMetadata
         * @param deferredImportSelector
         */
        @Override
        public void process(AnnotationMetadata annotationMetadata, DeferredImportSelector deferredImportSelector) {
            System.out.println("=====DeferredImportSelectorGroupDemo.process");
            String[] strings = deferredImportSelector.selectImports(annotationMetadata);
            for (String string : strings) {
                list.add(new Entry(annotationMetadata, string));
            }
        }

        /**
         * 把收集到的类返回给spring容器
         * @return
         */
        @Override
        public Iterable<Entry> selectImports() {
            System.out.println("=====DeferredImportSelectorGroupDemo.selectImports");
            return list;
        }
    }

}
