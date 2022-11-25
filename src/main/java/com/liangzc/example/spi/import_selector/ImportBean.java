package com.liangzc.example.spi.import_selector;


import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import(DeferredImportSelectorDemo.class)
public class ImportBean {
}
